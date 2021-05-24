package com.company;

import com.company.Crawler.URLDepthPair;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

class Crawler$CrawlerThread implements Runnable {
    private URLDepthPair link;

    public Crawler$CrawlerThread(Crawler var1, URLDepthPair link_) {
        this.this$0 = var1;
        this.link = link_;
    }

    public void run() {
        if (!Crawler.access$000(this.this$0).containsKey(this.link.getURL())) {
            Crawler.access$000(this.this$0).put(this.link.getURL(), this.link);
            if (this.link.getDepth() < Crawler.access$100(this.this$0)) {
                try {
                    URL url = new URL(this.link.getURL());
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    Scanner s = new Scanner(con.getInputStream());
                    Pattern LINK_REGEX = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1");

                    while(true) {
                        String newURL;
                        do {
                            if (s.findWithinHorizon(LINK_REGEX, 0) == null) {
                                return;
                            }

                            newURL = s.match().group(2);
                            if (newURL.startsWith("/")) {
                                newURL = this.link.getURL() + newURL;
                                break;
                            }
                        } while(!newURL.startsWith("http"));

                        URLDepthPair newLink = new URLDepthPair(this.this$0, newURL, this.link.getDepth() + 1);
                        Crawler.access$200(this.this$0).add(newLink);
                    }
                } catch (Exception var7) {
                }
            }
        }
    }
}

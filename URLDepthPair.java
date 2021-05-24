package com.company;

class Crawler$URLDepthPair {
    private String url;
    private int depth;

    public Crawler$URLDepthPair(Crawler this$0, String url, int depth) {
        this.this$0 = this$0;
        this.url = url;
        this.depth = depth;
    }

    public String getURL() {
        return this.url;
    }

    public int getDepth() {
        return this.depth;
    }

    public String toString() {
        return "Глубина: " + this.depth + "\tURL: " + this.url;
    }
}

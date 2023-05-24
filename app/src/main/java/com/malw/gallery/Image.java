package com.malw.gallery;

public class Image {
    private final String title;
    private final String path;

    Image(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getPath() {return this.path;}
    public String getTitle() {return this.title;}
}

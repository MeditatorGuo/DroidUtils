package com.uowee.droid.model;

/**
 * Created by GuoWee on 2018/1/30.
 */

public class GridItem {

    private String title;
    private int image;

    public GridItem(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GridItem{" +
                "title='" + title + '\'' +
                ", image=" + image +
                '}';
    }
}

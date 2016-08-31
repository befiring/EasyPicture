package com.befiring.easypicture.bean;

/**
 * Created by Administrator on 2016/8/31.
 */
public class Image {
    public String description;
    public String image_url;

    public Image(String description, String image_url) {
        this.description = description;
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }
}

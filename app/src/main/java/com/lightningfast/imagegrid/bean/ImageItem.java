package com.lightningfast.imagegrid.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * 作者：lv
 * 创建时间：07月31日
 * 时间：18:14
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class ImageItem implements Serializable {

    public String imageId;//图片Id
    public String thumbnailPath;//缩略图地址
    public String imagePath;//图片的地址
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageId() {
        return imageId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }
}

package com.haoxitech.canzhaopin.utils.mutiphotochooser.model;

import java.io.Serializable;

/**
 * Created by wangtao on 16/1/29.
 */
public class ImageItem implements Serializable {
    public String imagePath;
    public String imageId;
    public String thumbnailPath = "";
    public boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

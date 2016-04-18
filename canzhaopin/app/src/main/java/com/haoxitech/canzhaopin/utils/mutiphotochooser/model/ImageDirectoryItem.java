package com.haoxitech.canzhaopin.utils.mutiphotochooser.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wangtao on 16/2/1.
 */
public class ImageDirectoryItem implements Serializable {
    public int imageCount = 0;
    public String directoryName;
    public ArrayList<ImageItem> imageItemArrayList = new ArrayList<>();

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public ArrayList<ImageItem> getImageItemArrayList() {
        return imageItemArrayList;
    }

    public void setImageItemArrayList(ArrayList<ImageItem> imageItemArrayList) {
        this.imageItemArrayList = imageItemArrayList;
    }
}

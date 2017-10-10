package com.lightningfast.imagegrid;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：lv
 * 创建时间：08月03日
 * 时间：10:07
 * 版本：v1.0.0
 * 类描述：用来控制图片选择的管理者
 * 修改时间：
 */
public class ImageSelectManage {


    private static final int DEFAULT_IMAGE_COUNT = 9;

    private List<String> mImageSelect = new ArrayList<>();
    private int mImageCount = DEFAULT_IMAGE_COUNT;

    private static ImageSelectManage sImageSelectManage;

    private InputType mInputType;


    private ImageSelectManage() {
    }

    private ImageSelectManage(InputType inputType) {
        mInputType = inputType;
    }

    public static ImageSelectManage getInstance(InputType inputType) {
        if (sImageSelectManage == null) {
            sImageSelectManage = new ImageSelectManage(inputType);
        } else {
            sImageSelectManage.mInputType = inputType;
        }
        return sImageSelectManage;
    }

    public void setImageMaxCount(int imageCount) {
        mImageCount = imageCount;
    }

    public int length() {
        return mImageSelect.size();
    }

    public int getDefaultImageCount() {
        return DEFAULT_IMAGE_COUNT;
    }


    public boolean isContainsImage(String path) {
        return mImageSelect.contains(path);
    }

    public boolean removeImage(String imagePath) {
        return mImageSelect.remove(imagePath);
    }

    public ImageHolder addImageForSet(String imagePath) {
        ImageHolder holder = new ImageHolder();
        //根据用户加入类型的不同，选择不同的方式
        if (mInputType == InputType.FIXED) {//加入固定的数量的容器中
            holder.isAddSuccess = setImageForFixedContainer(imagePath);
            holder.imageCount = mImageSelect.size();
        } else if (mInputType == InputType.INDEX) {//加入无限制的容器中
            holder.isAddSuccess = setImageForIndexContainer(imagePath);
            holder.imageCount = mImageSelect.size();
        }
        return holder;
    }

    public ArrayList<String> getImageList() {
        ArrayList<String> imageList = new ArrayList<>();
        imageList.addAll(mImageSelect);
        return imageList;
    }

    public void onActivityFinish() {
        mImageSelect.clear();
        sImageSelectManage = null;
    }


    /**
     * 将Image放入到数量固定的集合容器中
     *
     * @param imagePath 图片的绝对路径
     * @return 是否添加成功
     */
    private boolean setImageForFixedContainer(String imagePath) {
        if (mImageSelect.size() < mImageCount) {
            return mImageSelect.add(imagePath);
        } else {
            return false;
        }
    }

    /**
     * 将Image放入到返回图片数量的集合容器中，注意这两个只能选择一种
     *
     * @param imagePath 图片的绝对路径
     * @return 添加的数量
     */
    private boolean setImageForIndexContainer(String imagePath) {
        return mImageSelect.add(imagePath);
    }


    public enum InputType {
        FIXED,//加入固定的存储容器
        INDEX//加入无限制的存储容器
    }

    public class ImageHolder {
        public int imageCount;
        public boolean isAddSuccess;
    }

}

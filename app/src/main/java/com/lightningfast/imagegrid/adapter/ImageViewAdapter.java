package com.lightningfast.imagegrid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lightningfast.R;
import com.lightningfast.imagegrid.ImageSelectManage;

import java.util.List;

/**
 * 作者：lv
 * 创建时间：07月31日
 * 时间：16:05
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class ImageViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int SINGLE_PHOTO_CAMERA = 3;

    private Context mContext;
    private List<String> mImagePaths;
    private LayoutInflater mInflater;
    private int mImageSize;

    private BaseViewHolder.OnItemClickListener mItemClickListener;
    private OnImageCountChangeListener mSelectImageListener;

    private ImageSelectManage mImageSelectManage;
    private ItemType mItemType;

    public enum ItemType {
        CAMERA, PHOTO
    }

    public ItemType getItemType() {
        return mItemType;
    }

    public ImageViewAdapter(Context context, List<String> images) {

        mContext = context;
        mImagePaths = images;
        mInflater = LayoutInflater.from(context);

        mImageSize = getWidthPx() / 3;

        mImageSelectManage = ImageSelectManage.getInstance(ImageSelectManage.InputType.FIXED);

    }

    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItemType(ItemType type) {
        mItemType = type;
    }

    /**
     * 设置图片选择的监听器
     *
     * @param listener
     */
    public void setOnImageCountChangeListener(OnImageCountChangeListener listener) {
        mSelectImageListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemType == ItemType.CAMERA && position == 0) {
            return SINGLE_PHOTO_CAMERA;
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        switch (viewType) {
            case SINGLE_PHOTO_CAMERA:
                View cameraView = mInflater.inflate(R.layout.item_camara, null);
                holder = new CameraHolder(cameraView);
                break;
            default:
                View rootView = mInflater.inflate(R.layout.item_grid, null);
                holder = new ImageViewHolder(rootView);
                holder.setOnItemClickListener(mItemClickListener);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {

        int itemType = getItemViewType(position);
        switch (itemType) {
            case SINGLE_PHOTO_CAMERA:
                CameraHolder cameraHolder = (CameraHolder) holder;
                cameraHolder.llCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onClickCamera(holder, v);
                        }
                    }
                });
                break;
            default:
                final ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                String filePath = "";
                if (mItemType == ItemType.CAMERA) {
                    filePath = mImagePaths.get(position-1);
                } else {
                    filePath = mImagePaths.get(position);
                }
                //初始化状态
                imageViewHolder.iv.setColorFilter(null);
                imageViewHolder.ivBtn.setImageResource(R.drawable.ic_check_box_no);

                Glide.with(mContext).load(filePath).thumbnail(0.1f).into(imageViewHolder.iv);

                if (mImageSelectManage.isContainsImage(filePath)) {
                    imageViewHolder.iv.setColorFilter(Color.parseColor("#77000000"));
                    imageViewHolder.ivBtn.setImageResource(R.drawable.ic_check_box_ok);
                }


                imageViewHolder.ivBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String clickFilePath="";
                        if (mItemType == ItemType.CAMERA) {
                            clickFilePath = mImagePaths.get(imageViewHolder.getLayoutPosition()-1);
                        }else {
                            clickFilePath = mImagePaths.get(imageViewHolder.getLayoutPosition());
                        }
                        if (mImageSelectManage.isContainsImage(clickFilePath)) {
                            mImageSelectManage.removeImage(clickFilePath);
                            imageViewHolder.iv.setColorFilter(null);
                            imageViewHolder.ivBtn.setImageResource(R.drawable.ic_check_box_no);

                        } else {
                            ImageSelectManage.ImageHolder imageHolder = mImageSelectManage.addImageForSet(clickFilePath);
                            if (!imageHolder.isAddSuccess) {
                                Toast.makeText(mContext, "已达到最大添加数量", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            imageViewHolder.iv.setColorFilter(Color.parseColor("#77000000"));
                            imageViewHolder.ivBtn.setImageResource(R.drawable.ic_check_box_ok);
                        }

                        if (mSelectImageListener != null) {
                            mSelectImageListener.onCountChange(mImageSelectManage.length());
                        }

                    }
                });
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mItemType == ItemType.CAMERA ? mImagePaths.size() +1 : mImagePaths.size();
    }

    public class ImageViewHolder extends BaseViewHolder {
        ImageView iv;
        ImageButton ivBtn;

        public ImageViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_image);
            //设置图片的大小
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv.getLayoutParams();
            if (params == null) {
                params = new RelativeLayout.LayoutParams(mImageSize, mImageSize);
            } else {
                params.width = mImageSize;
                params.height = mImageSize;
            }
            iv.setLayoutParams(params);
            ivBtn = (ImageButton) itemView.findViewById(R.id.ivBtn_select);
        }
    }

    private class CameraHolder extends BaseViewHolder {
        private LinearLayout llCamera;

        public CameraHolder(View itemView) {
            super(itemView);
            llCamera = (LinearLayout) itemView.findViewById(R.id.ll_camera);
            //设置图片的大小
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) llCamera.getLayoutParams();
            if (params == null) {
                params = new RelativeLayout.LayoutParams(mImageSize, mImageSize);
            } else {
                params.width = mImageSize;
                params.height = mImageSize;
            }
            llCamera.setLayoutParams(params);
        }

    }

    private int getWidthPx() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 当有图片被选中的时候执行的回调接口
     */
    public interface OnImageCountChangeListener {
        /**
         * 当前选中图片的个数
         *
         * @param imageCount
         */
        void onCountChange(int imageCount);
    }

}

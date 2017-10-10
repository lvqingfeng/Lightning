package com.lightningfast.ui.manager.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.base.SDKApplication;
import com.lightningfast.databinding.AddImageFooterBinding;
import com.lightningfast.databinding.ReleaseReputationImageBinding;
import com.lightningfast.imagegrid.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：lv
 * 创建时间：11月14日
 * 时间：17:12
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class ReleaseReputationImageAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int IMAGE_ITEM = 1;
    private static final int ADD_IMAGE = 2;
    private int MAX_Image;
    private List<String> mListData;
    private Activity mActivity;
    private int mRequestCode;
    private int mImageSize;

    public ReleaseReputationImageAdapter(Activity mActivity,int mRequestCode, int MAX_Image) {
        this.mRequestCode = mRequestCode;
        this.MAX_Image = MAX_Image;
        this.mActivity = mActivity;
        mImageSize = SDKApplication.sWindowWidth / 3;
        mListData = new ArrayList<>();
    }

    public ReleaseReputationImageAdapter(Activity activity, int requestCode) {
        mActivity = activity;
        mImageSize = SDKApplication.sWindowWidth / 3;
        mListData = new ArrayList<>();
        mRequestCode = requestCode;
    }

    public void addImageUrl(List<String> listData) {
        mListData.clear();
        mListData.addAll(listData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < MAX_Image && position == mListData.size() || mListData.size() == 0)
            return ADD_IMAGE;
        return IMAGE_ITEM;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case IMAGE_ITEM:
                View imageItemView = inflater.inflate(R.layout.item_layout_release_reputation_image, parent, false);
                return new ItemImageViewHolder(imageItemView);
            case ADD_IMAGE:
                View addImage = inflater.inflate(R.layout.item_footer_add_image, parent, false);
                return new AddImageViewHolder(addImage);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case IMAGE_ITEM:
                ReleaseReputationImageBinding itemBinding = (ReleaseReputationImageBinding) holder.getBinding();
                Glide.with(holder.mContext).load(mListData.get(position)).into(itemBinding.image);
                break;
            case ADD_IMAGE:
//                int layoutSize = mImageSize - DensityUtil.dip2px(holder.mContext, 10);
//                AddImageFooterBinding binding = (AddImageFooterBinding) holder.getBinding();
//                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) binding.llParent.getLayoutParams();
//                if (lp == null) {
//                    lp = new RecyclerView.LayoutParams(layoutSize, layoutSize);
//                } else {
//                    lp.width = layoutSize;
//                    lp.height = layoutSize;
//                }
//                binding.llParent.setLayoutParams(lp);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size() < MAX_Image ? mListData.size() + 1 : MAX_Image;
    }

    private class ItemImageViewHolder extends BindingViewHolder<ReleaseReputationImageBinding> {

        ItemImageViewHolder(View view) {
            super(ReleaseReputationImageBinding.bind(view));
        }
    }

    private class AddImageViewHolder extends BindingViewHolder<AddImageFooterBinding>
            implements View.OnClickListener {
        private View addImage;

        AddImageViewHolder(View view) {
            super(AddImageFooterBinding.bind(view));
            addImage = view;
            addImage.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            ImageGridActivity.actionStart(mActivity, MAX_Image, (ArrayList<String>) mListData, mRequestCode);
        }
    }

}

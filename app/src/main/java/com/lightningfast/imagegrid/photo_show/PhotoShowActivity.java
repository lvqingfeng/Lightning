package com.lightningfast.imagegrid.photo_show;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.base.SDKApplication;
import com.lightningfast.imagegrid.ImageSelectManage;
import com.lightningfast.imagegrid.adapter.ImageViewPagerAdapter;
import com.lightningfast.imagegrid.bean.ImageBucket;
import com.lightningfast.imagegrid.bean.ImageItem;
import com.lightningfast.imagegrid.utils.ImmersiveUtil;
import com.lightningfast.imagegrid.utils.InOutAnimationUtils;
import com.lightningfast.imagegrid.widget.HackyViewPager;
import com.lzp.statusbar.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;

public class PhotoShowActivity extends AppCompatActivity implements OnImageClickCallback {
    /**
     * 图片的展示类型
     * {@link #GALLERY_TYPE 图库类型}
     * {@link #PREVIEW_ALBUM_TYPE 预览类型}
     * {@link #PREVIEW_RANDOM_TYPE 随机类型}
     */
    public static final String SHOW_PHOTO_TYPE = "type";
    public static final String SELECT_POSITION = "select_position";//当前选中的第几条
    public static final String IMAGE_DATA_POSITION = "image_data_position";//图库中选择的类型条目
    //图库类型
    public static final String GALLERY_TYPE = "gallery";
    //预览类型(相册)
    public static final String PREVIEW_ALBUM_TYPE = "preview_album";
    //预览类型(随机添加)
    public static final String PREVIEW_RANDOM_TYPE = "preview_random";

    public static final String IMAGE_DATA = "image_data";//随机类型的数据源

    private int mSelectPosition;
    private int mImageDataPosition;

    private List<String> mImageData = new ArrayList<>();
    private HackyViewPager mViewPager;
    private Toolbar mToolbar;
    private ImageView mImageTitleBack;
    private TextView mTvTitleIndex;
    private View mStatusBar;
    private CheckBox mCbxTitleSelect;

    private ImageSelectManage mSelectManage;

    public static void actionStart(Context context, ArrayList<String> imageList, int selectPosition) {
        Intent intent = new Intent(context, PhotoShowActivity.class);
        intent.putStringArrayListExtra(PhotoShowActivity.IMAGE_DATA, imageList);
        intent.putExtra(PhotoShowActivity.SHOW_PHOTO_TYPE, PhotoShowActivity.PREVIEW_RANDOM_TYPE);
        intent.putExtra(PhotoShowActivity.SELECT_POSITION, selectPosition);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_show);

        mSelectManage = ImageSelectManage.getInstance(ImageSelectManage.InputType.FIXED);
        initViewId();
        Intent intent = getIntent();
        setStatusBarColor();
        String showPhotoType = intent.getStringExtra(SHOW_PHOTO_TYPE);
        mSelectPosition = intent.getIntExtra(SELECT_POSITION, 0);
        if (GALLERY_TYPE.equals(showPhotoType)) {
            mImageDataPosition = intent.getIntExtra(IMAGE_DATA_POSITION, 0);
            initData();
        } else if (PREVIEW_ALBUM_TYPE.equals(showPhotoType)) {
            ArrayList<String> list = mSelectManage.getImageList();
            mImageData.addAll(list);
        } else if (PREVIEW_RANDOM_TYPE.equals(showPhotoType)) {
            mCbxTitleSelect.setVisibility(View.GONE);
            ArrayList<String> list = intent.getStringArrayListExtra(IMAGE_DATA);
            mImageData.addAll(list);
        }

        setupData();
        initViews();
    }

    private void initViews() {


        mImageTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mCbxTitleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCbxTitleSelect.isChecked()) {
                    ImageSelectManage.ImageHolder holder = mSelectManage.addImageForSet(mImageData.get(mSelectPosition));
                    if (!holder.isAddSuccess) {
                        Toast.makeText(PhotoShowActivity.this, "已达最大添加上限", Toast.LENGTH_SHORT).show();
                        mCbxTitleSelect.setChecked(false);
                    }
                } else {
                    mSelectManage.removeImage(mImageData.get(mSelectPosition));
                }
            }
        });

        mTvTitleIndex.setText((mSelectPosition + 1) + "/" + mImageData.size());
        mViewPager.setCurrentItem(mSelectPosition);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSelectPosition = position;
                mTvTitleIndex.setText((mSelectPosition + 1) + "/" + mImageData.size());

                String imagePath = mImageData.get(mSelectPosition);
                if (mSelectManage.isContainsImage(imagePath)) {
                    mCbxTitleSelect.setChecked(true);
                } else {
                    if (mCbxTitleSelect.isChecked()) {
                        mCbxTitleSelect.setChecked(false);
                    }
                }


                if (mToolbar.getVisibility() == View.VISIBLE) {
                    fadeOut();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupData() {
        String imagePath = mImageData.get(mSelectPosition);
        if (mSelectManage.isContainsImage(imagePath)) {
            mCbxTitleSelect.setChecked(true);
        }

        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(getSupportFragmentManager(), mImageData);
        mViewPager.setAdapter(adapter);
    }

    private void initData() {

        //从数据库中获取数据
        List<ImageBucket> imageBuckets = SDKApplication.getApplication().getImageBucket();

        if (mImageDataPosition == 0) {
            loadAllImage(imageBuckets);
        } else {
            List<ImageItem> imageList = imageBuckets.get(mImageDataPosition - 1).imageList;
            addImageForList(imageList);
        }


    }

    private void initViewId() {

        mViewPager = (HackyViewPager) findViewById(R.id.vp_image);
        mToolbar = (Toolbar) findViewById(R.id.title_toolbar);
        mImageTitleBack = (ImageView) findViewById(R.id.iv_title_back);
        mTvTitleIndex = (TextView) findViewById(R.id.tv_image_index);

        mStatusBar = findViewById(R.id.activity_photo_show_status_bar);
        mCbxTitleSelect = (CheckBox) findViewById(R.id.cbx_layout_photo_show_title);
    }


    /**
     * 加载所有的图片
     *
     * @param imageBuckets 从数据库中读取到的图片集合
     */
    private void loadAllImage(List<ImageBucket> imageBuckets) {
        for (int i = imageBuckets.size() - 1; i >= 0; i--) {
            List<ImageItem> imageItemList = imageBuckets.get(i).imageList;
            addImageForList(imageItemList);
        }

    }

    /**
     * 将图片添加到展示集合中
     *
     * @param imageItemList
     */
    private void addImageForList(List<ImageItem> imageItemList) {
        for (int j = imageItemList.size() - 1; j >= 0; j--) {
            ImageItem imageItem = imageItemList.get(j);
            mImageData.add(imageItem.imagePath);
        }
    }


    void fadeIn() {
        InOutAnimationUtils.animateIn(mToolbar, R.anim.viewer_toolbar_fade_in);
        InOutAnimationUtils.animateIn(mStatusBar, R.anim.viewer_toolbar_fade_in);
        ImmersiveUtil.exit(this);
    }

    void fadeOut() {
        InOutAnimationUtils.animateOut(mToolbar, R.anim.viewer_toolbar_fade_out);
        InOutAnimationUtils.animateOut(mStatusBar, R.anim.viewer_toolbar_fade_out);
        ImmersiveUtil.enter(this);
    }

    @Override
    public void onClick(View v) {
        if (View.INVISIBLE == mToolbar.getVisibility()) {
            fadeIn();
        } else if (View.VISIBLE == mToolbar.getVisibility()) {
            fadeOut();
        }
    }

    private void setStatusBarColor() {

        Drawable drawable = mToolbar.getBackground();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
            mStatusBar.setBackground(drawable);
        } else {
            mStatusBar.setVisibility(View.GONE);
        }


    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


}

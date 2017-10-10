package com.lightningfast.imagegrid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.base.SDKApplication;
import com.lightningfast.camera.SinglePictureSelectHelper;
import com.lightningfast.imagegrid.adapter.BaseViewHolder;
import com.lightningfast.imagegrid.adapter.ImageViewAdapter;
import com.lightningfast.imagegrid.bean.ImageBucket;
import com.lightningfast.imagegrid.bean.ImageItem;
import com.lightningfast.imagegrid.photo_show.PhotoShowActivity;
import com.lightningfast.view.DividerGridItemDecoration;
import com.lzp.statusbar.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageGridActivity extends AppCompatActivity {

    public static final String MAX_IMAGE_COUNT = "max_image";

    public static final String RESULT_DATA = "result_data";

    private RecyclerView mRecyclerView;
    private List<String> mImages = new ArrayList<>();

    private LinearLayout mLayoutBottom;
    private TextView mTvDirName;
    private TextView mTvImageCount;

    private SelectImageDirPopupWindow mPopupWindow;

    private int mLayoutBottomHeight;
    public int mImageDataPosition;
    private View mStatusBar;
    private Toolbar mToolbar;
    private ImageView mImageTitleBack;
    private TextView mTvTitleSubmit;

    private int mImageMaxCount;
    private TextView mTvPreviewShow;
    private ImageSelectManage mImageSelectManage;
    private ImageViewAdapter mAdapter;

    private SinglePictureSelectHelper mSinglePicturHelper;

    public static void actionStart(Activity activity, int imageMaxCount, ArrayList<String> selectedImage, int requestCode) {
        Intent intent = new Intent(activity, ImageGridActivity.class);
        intent.putExtra(MAX_IMAGE_COUNT, imageMaxCount);
        intent.putExtra("selected_image", selectedImage);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_grid);

        mSinglePicturHelper = SinglePictureSelectHelper.getInstance();
        mSinglePicturHelper.init(this);

        if (mRecyclerView != null && mRecyclerView.getAdapter() != null) {
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }

        Intent intent = getIntent();
        mImageMaxCount = intent.getIntExtra(MAX_IMAGE_COUNT, 0);
        ArrayList<String> selectedImageList = intent.getStringArrayListExtra("selected_image");
        mImageSelectManage = ImageSelectManage.getInstance(ImageSelectManage.InputType.FIXED);
        mImageSelectManage.setImageMaxCount(mImageMaxCount);
        if (selectedImageList != null) {
            for (String s : selectedImageList) {
                mImageSelectManage.addImageForSet(s);
            }
        }
        initViewId();

        setStatusBarColor();

        onClick();

        //从数据库中获取数据
        List<ImageBucket> imageBuckets = SDKApplication.getApplication().getImageBucket();
        if (imageBuckets.size() <= 0) {
            Toast.makeText(ImageGridActivity.this, "未检测到您的图片库", Toast.LENGTH_SHORT).show();
            return;
        }
        //初始化基本控件
        initViews(imageBuckets);
        //初始化数据
        initDatas(imageBuckets);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mRecyclerView != null && mRecyclerView.getAdapter() != null) {
            if (mTvTitleSubmit != null) {
                if (mImageSelectManage.length() > 0) {
                    mTvTitleSubmit.setEnabled(true);
                    mTvPreviewShow.setEnabled(true);
                    mTvTitleSubmit.setText("提交(" + mImageSelectManage.length() + "/" + mImageMaxCount + ")");
                } else if (mImageSelectManage.length() == 0) {
                    mTvPreviewShow.setEnabled(false);
                    mTvTitleSubmit.setEnabled(false);
                    mTvTitleSubmit.setText("提交");
                }

            }
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImageSelectManage.onActivityFinish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mLayoutBottomHeight = mLayoutBottom.getHeight();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String cameraImageUrl = mSinglePicturHelper.onActivityResult(requestCode, resultCode, data, null, false);
        if (!TextUtils.isEmpty(cameraImageUrl)) {

            mImageSelectManage.addImageForSet(cameraImageUrl);
            submitSelectImage();
        }
    }

    /**
     * 初始化基本控件
     *
     * @param imageBuckets
     */
    private void initViews(final List<ImageBucket> imageBuckets) {

        mPopupWindow = new SelectImageDirPopupWindow(this, imageBuckets);
        mPopupWindow.setOnPopupSelectListener(new SelectImageDirPopupWindow.OnPopupSelectListener<ImageBucket>() {


            @Override
            public void onSelect(ImageBucket imageBucket, int position) {
                mImages.clear();
                mImageDataPosition = position;
                if (position == 0) {
                    loadAllImage(imageBuckets);
                    mAdapter.setItemType(ImageViewAdapter.ItemType.CAMERA);
                } else {
                    mAdapter.setItemType(ImageViewAdapter.ItemType.PHOTO);
                    List<ImageItem> imageItems = imageBucket.imageList;
                    addImageForList(imageItems);
                    mTvImageCount.setText(String.valueOf(imageBucket.count));
                    mTvDirName.setText(imageBucket.bucketName);
                }
                mAdapter.notifyDataSetChanged();
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));


    }

    private void onClick() {
        //点击显示弹出框
        mLayoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopupWindow == null) {
                    Toast.makeText(ImageGridActivity.this, "未检测到您的图片库，无法显示目录列表", Toast.LENGTH_SHORT).show();
                } else {
                    lightOff();
                    mPopupWindow.showAsDropDown(mLayoutBottom, 0, -mLayoutBottomHeight);
                    mPopupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
                }

            }
        });

        mImageTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //预览按钮
        mTvPreviewShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageGridActivity.this, PhotoShowActivity.class);
                intent.putExtra(PhotoShowActivity.SHOW_PHOTO_TYPE, PhotoShowActivity.PREVIEW_ALBUM_TYPE);
                startActivity(intent);
            }
        });
        //提交选择按钮
        mTvTitleSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitSelectImage();
            }
        });
    }

    /**
     * 提交选择的图片
     */
    private void submitSelectImage() {
        //Toast.makeText(ImageGridActivity.this, "提交选择内容", Toast.LENGTH_SHORT).show();
        ArrayList<String> list = mImageSelectManage.getImageList();
        setResult(RESULT_OK, new Intent().putStringArrayListExtra(RESULT_DATA, list));
        finish();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("---要提交的内容---" + list.get(i));
        }
    }

    /**
     * 初始化基本数据
     *
     * @param imageBuckets
     */
    private void initDatas(List<ImageBucket> imageBuckets) {

        loadAllImage(imageBuckets);
        mAdapter = new ImageViewAdapter(this, mImages);
        mAdapter.setItemType(ImageViewAdapter.ItemType.CAMERA);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder vh, View v) {

                Intent intent = new Intent(ImageGridActivity.this, PhotoShowActivity.class);
                intent.putExtra(PhotoShowActivity.SHOW_PHOTO_TYPE, PhotoShowActivity.GALLERY_TYPE);
                intent.putExtra(PhotoShowActivity.IMAGE_DATA_POSITION, mImageDataPosition);
                int clickPosition = 0;
                if (mAdapter.getItemType() == ImageViewAdapter.ItemType.CAMERA) {
                    clickPosition = vh.getLayoutPosition() - 1;
                } else {
                    clickPosition = vh.getLayoutPosition();
                }
                intent.putExtra(PhotoShowActivity.SELECT_POSITION, clickPosition);
                startActivity(intent);
            }

            @Override
            public void onClickCamera(RecyclerView.ViewHolder vh, View v) {
                if (mImageSelectManage.getImageList().size() >= mImageMaxCount) {
                    Toast.makeText(ImageGridActivity.this, "已达到最大添加数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                mSinglePicturHelper.openCameraForFile();
            }
        });
        mAdapter.setOnImageCountChangeListener(new ImageViewAdapter.OnImageCountChangeListener() {
            @Override
            public void onCountChange(int imageCount) {
                if (!mTvPreviewShow.isEnabled()) {
                    mTvPreviewShow.setEnabled(true);
                }
                if (!mTvTitleSubmit.isEnabled()) {
                    mTvTitleSubmit.setEnabled(true);
                }
                if (imageCount == 0) {
                    mTvPreviewShow.setEnabled(false);
                    mTvTitleSubmit.setEnabled(false);
                    mTvTitleSubmit.setText("提交");
                } else {
                    mTvTitleSubmit.setText("提交(" + imageCount + "/" + mImageMaxCount + ")");
                }
            }
        });
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
        mTvImageCount.setText(String.valueOf(mImages.size()));
        mTvDirName.setText("所有图片");
    }

    /**
     * 将图片添加到展示集合中
     *
     * @param imageItemList
     */
    private void addImageForList(List<ImageItem> imageItemList) {
        for (int j = imageItemList.size() - 1; j >= 0; j--) {
            ImageItem imageItem = imageItemList.get(j);
            mImages.add(imageItem.imagePath);
        }
    }

    private void initViewId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mLayoutBottom = (LinearLayout) findViewById(R.id.layout_bottom);
        mTvDirName = (TextView) findViewById(R.id.tv_name);
        mTvImageCount = (TextView) findViewById(R.id.tv_num);
        mStatusBar = findViewById(R.id.activity_image_gird_status_bar);

        mToolbar = (Toolbar) findViewById(R.id.title_toolbar);
        mImageTitleBack = (ImageView) findViewById(R.id.iv_title_back);
        mTvTitleSubmit = (TextView) findViewById(R.id.tv_submit);
        mTvPreviewShow = (TextView) findViewById(R.id.tv_preview_show);
    }

    /**
     * 屏幕变亮
     */
    private void lightOn() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    /**
     * 屏幕变暗
     */
    private void lightOff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }


    /*下面是设置状态栏的颜色*/

    private void setStatusBarColor() {

        Drawable drawable = mToolbar.getBackground();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(ImageGridActivity.this);
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

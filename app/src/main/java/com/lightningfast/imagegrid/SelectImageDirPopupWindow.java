package com.lightningfast.imagegrid;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lightningfast.R;
import com.lightningfast.imagegrid.bean.ImageBucket;
import com.lightningfast.imagegrid.bean.ImageItem;

import java.io.File;
import java.util.List;

/**
 * 作者：吕振鹏
 * 创建时间：08月03日
 * 时间：10:25
 * 版本：v1.0.0
 * 类描述：弹窗显示图片目录
 * 修改时间：
 */
public class SelectImageDirPopupWindow extends PopupWindow {

    private Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    private int mWidth;
    private int mHeight;

    private ListView mDirListView;
    private List<ImageBucket> mListData;

    private OnPopupSelectListener<ImageBucket> mSelectListener;

    public SelectImageDirPopupWindow(Context context, List<ImageBucket> listData) {

        mContext = context;
        mListData = listData;
        mInflater = LayoutInflater.from(context);

        getWindowSize(context);
        setWidth(mWidth);
        setHeight(mHeight);

        mContentView = mInflater.inflate(R.layout.popup_layout, null);
        mDirListView = (ListView) mContentView.findViewById(R.id.lv_popup_layout);

        // 设置SelectPicPopupWindow弹出窗体可点击
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setContentView(mContentView);
        setBackgroundDrawable(new ColorDrawable(0x77000000));

        initViews();
        initEvent();


    }

    /**
     * 设置图片目录选择的回调监听器
     *
     * @param listener
     */
    public void setOnPopupSelectListener(OnPopupSelectListener<ImageBucket> listener) {
        mSelectListener = listener;
    }

    private void initEvent() {

        mDirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSelectListener != null) {
                    if (position == 0) {
                        mSelectListener.onSelect(null, position);
                    } else {
                        mSelectListener.onSelect(mListData.get(position - 1), position);
                    }
                }
            }
        });

    }

    private void initViews() {

        View headerView = mInflater.inflate(R.layout.item_poput_layout_list, null);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.iv_dir_image);

        String imagePath = mListData.get(mListData.size() - 1).imageList.get(mListData.get(mListData.size() - 1).imageList.size() - 1).imagePath;
        Glide.with(mContext).load(imagePath).thumbnail(0.1f).into(imageView);

        TextView dirName = (TextView) headerView.findViewById(R.id.tv_dir_name);
        dirName.setText("所有文件");

        int imageCount = 0;
        for (ImageBucket imageBucket : mListData) {
            imageCount += imageBucket.count;
        }
        TextView dirNum = (TextView) headerView.findViewById(R.id.tv_dir_num);
        dirNum.setText(mContext.getString(R.string.image_num, String.valueOf(imageCount)));

        mDirListView.addHeaderView(headerView);
        mDirListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mListData.size();
            }


            @Override
            public ImageBucket getItem(int position) {
                return mListData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHolder holder = null;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.item_poput_layout_list, parent, false);
                    holder.iv = (ImageView) convertView.findViewById(R.id.iv_dir_image);
                    holder.dirName = (TextView) convertView.findViewById(R.id.tv_dir_name);
                    holder.dirNum = (TextView) convertView.findViewById(R.id.tv_dir_num);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                ImageBucket imageBucket = getItem(position);
                ImageItem imageItem = imageBucket.imageList.get(imageBucket.imageList.size() - 1);
                Glide.with(mContext).load(new File(imageItem.imagePath)).thumbnail(0.1f).into(holder.iv);
                holder.dirName.setText(imageBucket.bucketName);
                holder.dirNum.setText(mContext.getString(R.string.image_num, String.valueOf(imageBucket.count)));

                return convertView;
            }

            class ViewHolder {
                ImageView iv;
                TextView dirName;
                TextView dirNum;
                RadioButton rdoBtn;
            }
        });

    }

    private void getWindowSize(Context context) {

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);

        mWidth = metrics.widthPixels;
        mHeight = (int) (metrics.heightPixels * 0.8);
    }

    /**
     * 图片列表的目录选择回调接口
     *
     * @param <T>
     */
    public interface OnPopupSelectListener<T> {
        void onSelect(T t, int position);
    }

}

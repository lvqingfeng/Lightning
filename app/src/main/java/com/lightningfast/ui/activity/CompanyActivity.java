package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.CompanyDataBean;
import com.lightningfast.databinding.ActivityCompanyBinding;
import com.lightningfast.uitls.LoginHelper;

/***
 * 单位信息的界面
 * */
public class CompanyActivity extends BaseActivity<ActivityCompanyBinding> {
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, CompanyActivity.class);
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
    protected void initViews() {

        updateCompany();

        mDataBinding.nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactsActivity.actionStart(CompanyActivity.this);
            }
        });
    }

    private void updateCompany(){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .updateCompanyInfo(LoginHelper.getInstance().getUserId(),LoginHelper.getInstance().getUserToken())
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<CompanyDataBean>() {
            @Override
            public void onNext(CompanyDataBean result) {
                if (result.getCode()==1){
                    CompanyDataBean.DataBean data = result.getData();
                    mDataBinding.companyName.setText(data.getCompany_name());
                    mDataBinding.companyNature.setText(data.getCompany_nature());
                    mDataBinding.salary.setText(data.getSalary());
                    mDataBinding.companyAddress.setText(data.getCompany_address());
                    mDataBinding.workYears.setText(data.getWork_years());
                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        }));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AddPersonDataActivity.EDITPERSON_REQUESTCODE){
            if (resultCode==RESULT_OK){
                updateCompany();
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company;
    }
}

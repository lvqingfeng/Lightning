package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.PersonDataBean;
import com.lightningfast.databinding.ActivityPersonDataBinding;
import com.lightningfast.uitls.LoginHelper;

/***
 * 个人信息
 * */
public class PersonDataActivity extends BaseActivity<ActivityPersonDataBinding> {

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PersonDataActivity.class);
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
        updatePersonData();
//        getRightView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddPersonDataActivity.actionStart(PersonDataActivity.this, AddPersonDataActivity.EDITPERSON_REQUESTCODE);
//            }
//        });
    }

    private void updatePersonData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getPersonData(LoginHelper.getInstance().getUserId(),LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext
                , new SubscriberOnNextListener<PersonDataBean>() {
            @Override
            public void onNext(PersonDataBean result) {
                if (result.getCode()==2){
                    toLogin();
                }
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AddPersonDataActivity.EDITPERSON_REQUESTCODE){
            if (resultCode==RESULT_OK){
                updatePersonData();
            }
        }
    }
}

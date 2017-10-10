package com.lightningfast;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.bean.TokenBean;
import com.lightningfast.ui.MainActivity;
import com.lightningfast.ui.activity.LoginActivity;
import com.lightningfast.uitls.LoginHelper;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Subscriber;
import rx.functions.Action1;

public class SplashActivity extends AppCompatActivity {
    private static final int sleepTime = 2400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIsLogin();
    }

    private void checkIsLogin(){
        RxPermissions.getInstance(getApplicationContext()).request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                       if (aBoolean){
                           nextStep();
                       }
                    }
                });

    }
    private void nextStep(){
        if (LoginHelper.getInstance().isOnline()){
            RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).updateToken(LoginHelper.getInstance().getUserId()), new Subscriber<TokenBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    startLogin();
                }

                @Override
                public void onNext(TokenBean result) {
                    if (result.getCode()==1){
                        if (LoginHelper.getInstance().getUserToken().equals(result.getData())){
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                Toast.makeText(SplashActivity.this, "请前往设置中心打开权限", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            MainActivity.actionStart(SplashActivity.this);
                            finish();
                        }else {
                            startLogin();
                        }
                    }else {
                        startLogin();
                    }
                }
            });
        }else {
            startLogin();

        }
    }
    private void startLogin(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LoginActivity.actionStart(SplashActivity.this,LoginActivity.FROM_GUIDE);
                finish();
            }
        }).start();
    }

}

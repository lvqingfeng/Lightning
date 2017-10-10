package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lightningfast.Constant;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.EmployeeBean;
import com.lightningfast.bean.LoginBean;
import com.lightningfast.bean.User;
import com.lightningfast.databinding.ActivityChooseEmployeeBinding;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.MainActivity;
import com.lightningfast.ui.adapter.EmployeeAdapter;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.uitls.UserUtils;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;

public class ChooseEmployeeActivity extends BaseActivity<ActivityChooseEmployeeBinding>
implements SpringView.OnFreshListener{
    private String uid;
    private String phone;
    private String password;
    private boolean isEnd=true;
    private List<EmployeeBean.DataBean.EmpInfoBean>  mList;
    private EmployeeAdapter adapter;
    public static void actionStart(Activity activity, String uid,String phone,String password) {
        Intent intent = new Intent(activity, ChooseEmployeeActivity.class);
        intent.putExtra("uid",uid);
        intent.putExtra("phone",phone);
        intent.putExtra("password",password);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("选择业务经理",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        uid=getIntent().getStringExtra("uid");
        phone=getIntent().getStringExtra("phone");
        password=getIntent().getStringExtra("password");
        mList=new ArrayList<>();
        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultHeader(mContext));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        setListData(mList);
        adapter = new EmployeeAdapter(mList);
        mDataBinding.recyclerView.setAdapter(adapter);
        initLoadData();

        mDataBinding.recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                final String id = mList.get(vh.getLayoutPosition()).getId();
                String employee_name = mList.get(vh.getLayoutPosition()).getEmployee_name();
                DialogUtils.alertDialogNormal(mContext, "温馨提示", "您确定要选择" + employee_name + "作为您的业务经理?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                }, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        addE(id);
                    }
                });
            }
        });
    }
    private void addE(String id){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .addEmployee(uid, id), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    logins();
                }else if (result.getCode()==2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void logins(){
        DialogUtils.showProgressDialog(mContext, "正在登录中", "", "");
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .login(phone, password), new Subscriber<LoginBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                showMessage(e.getMessage());
                hideDialogError();
            }

            @Override
            public void onNext(LoginBean result) {
                if (result.getCode()==1){
                    Toast.makeText(ChooseEmployeeActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                    UserUtils.putUserName(mContext,phone);
                    UserUtils.putUserPassword(mContext,password);
                    User user = UserUtils.getUser(mContext);
                    user.setPhone(phone);
                    user.setPassword(password);
                    user.setUid(result.getData().getCustomer_id());
                    user.setToken(result.getData().getToken());
                    UserUtils.saveUser(mContext,user);
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent().setAction(Constant.ACTION_PAGE_REFRESH));
                    LoginHelper.getInstance().setOnline(true);
                    MainActivity.actionStart(ChooseEmployeeActivity.this);
                    finish();
                }else {
                    showMessage(result.getMsg());
                    hideDialogError();
                }
            }
        });
    }
    private void hideDialogError() {
        DialogUtils.hideDialog(DialogUtils.LoadCompleteType.Error);
    }
    @Override
    protected void loadListData(int page) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).getEmployee("")
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<EmployeeBean>() {
            @Override
            public void onNext(EmployeeBean result) {
                if (result.getCode()==1){
                    mList.addAll(result.getData().getEmpInfo());
                    adapter.notifyDataSetChanged();
                    mDataBinding.springView.onFinishFreshAndLoad();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_employee;
    }

    @Override
    public void onRefresh() {
        onLoadMore(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }
}

package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.EmployeeBean;
import com.lightningfast.databinding.ActivityAddEmployeeBinding;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.adapter.EmployeeAdapter;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddEmployeeActivity extends BaseActivity<ActivityAddEmployeeBinding> {

    public static void actionStart(Activity activity,int requestCode) {
        Intent intent = new Intent(activity, AddEmployeeActivity.class);
        activity.startActivityForResult(intent,requestCode);
    }
    private List<EmployeeBean.DataBean.EmpInfoBean> mList;
    private EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        setTextTitleView("添加业务员",DEFAULT_TITLE_TEXT_COLOR);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mList=new ArrayList<>();
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        adapter=new EmployeeAdapter(mList);
        mDataBinding.recyclerView.setAdapter(adapter);
        loadData();

        mDataBinding.recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                final String id = mList.get(vh.getLayoutPosition()).getId();
                DialogUtils.alertDialogNormal(mContext, "温馨提示", "业务员一经添加无法修改,您确定要选此业务员?", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                }, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                                .addEmployee(LoginHelper.getInstance().getUserId(),id),new ProgressSubscriber<BaseBean>(mContext
                                , new SubscriberOnNextListener<BaseBean>() {
                            @Override
                            public void onNext(BaseBean result) {
                                if (result.getCode()==1){
                                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                                    setResult(RESULT_OK);
                                    finish();
                                }else {
                                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }));
                    }
                });
            }
        });
    }

    private void loadData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).getEmployee("")
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<EmployeeBean>() {
                    @Override
                    public void onNext(EmployeeBean result) {
                        if (result.getCode()==1){
                            mList.addAll(result.getData().getEmpInfo());
                            adapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_employee;
    }
}

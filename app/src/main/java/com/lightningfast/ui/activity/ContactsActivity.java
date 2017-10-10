package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityContactsBinding;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;
/***
 * 联系人的界面
 * */
public class ContactsActivity extends BaseActivity<ActivityContactsBinding>
implements SpringView.OnFreshListener{
    private boolean isEnd=true;
    private List<String> mList;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ContactsActivity.class);
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
//        getRightView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddContactsActivity.actionStart(ContactsActivity.this,AddContactsActivity.ADDCONTANTS_REQUESTCODE);
//            }
//        });
        mList=new ArrayList<>();
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        setListData(mList);
        initLoadData();
    }

    @Override
    protected void loadListData(int page) {

    }

    @Override
    public ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AddContactsActivity.ADDCONTANTS_REQUESTCODE){
            if (resultCode==RESULT_OK){
                onRefresh(isEnd);
            }
        }
    }
}

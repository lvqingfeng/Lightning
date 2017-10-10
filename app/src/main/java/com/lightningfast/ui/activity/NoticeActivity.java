package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.NoticeBean;
import com.lightningfast.databinding.ActivityNoticeBinding;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.adapter.NoticeAdapter;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class NoticeActivity extends BaseActivity<ActivityNoticeBinding> implements SpringView.OnFreshListener{

    private boolean isEnd;
    private List<NoticeBean.DataBean.ListBean> mList;
    private NoticeAdapter noticeAdapter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, NoticeActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("新消息",DEFAULT_TITLE_TEXT_COLOR);
    }


    @Override
    protected void initViews() {
        mList=new ArrayList<>();
        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        setListData(mList);
        noticeAdapter = new NoticeAdapter(mList);
        mDataBinding.recyclerView.setAdapter(noticeAdapter);
        initLoadData();
        mDataBinding.recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                if (mList.get(vh.getLayoutPosition()).getIs_read()==1){
                    return;
                }
                String id = mList.get(vh.getLayoutPosition()).getId();
                RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                        .updateIsRead(LoginHelper.getInstance().getUserId(),id,LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
                    @Override
                    public void onNext(BaseBean result) {
                        if (result.getCode()==1){
                            onRefresh();
                        }
                    }
                }));
            }
        });
    }

    @Override
    protected void loadListData(int page) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).updateNoticeList(LoginHelper.getInstance().getUserId()
                ,LoginHelper.getInstance().getUserToken(),String.valueOf(page),"15"),new ProgressSubscriber<NoticeBean>(mContext, new SubscriberOnNextListener<NoticeBean>() {
            @Override
            public void onNext(NoticeBean result) {
                if (result.getCode()==1){
                    isEnd=result.getData().getNo_more()==0;
                    mList.addAll(result.getData().getList());
                    noticeAdapter.notifyDataSetChanged();
                    mDataBinding.springView.onFinishFreshAndLoad();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    mDataBinding.springView.onFinishFreshAndLoad();
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
        return R.layout.activity_notice;
    }

    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }
}

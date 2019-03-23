package com.justwayward.booker.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.justwayward.booker.R;
import com.justwayward.booker.base.BaseFragment;
import com.justwayward.booker.bean.Recommend;
import com.justwayward.booker.component.AppComponent;
import com.justwayward.booker.component.DaggerRecommendFragmentComponent;
import com.justwayward.booker.ui.adapter.RecommendAdapter;
import com.justwayward.booker.ui.contract.RecommendContract;
import com.justwayward.booker.ui.presenter.RecommendPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class RecommendFragment extends BaseFragment implements RecommendContract.View {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    RecommendPresenter mPresenter;

    private RecommendAdapter mAdapter;
    private List<Recommend.RecommendBooks> mList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mPresenter.getRecommendList();
            }
        });

        mAdapter = new RecommendAdapter(mContext, mList);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.attachView(this);
        mPresenter.getRecommendList();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendFragmentComponent.builder()
                .appComponent(appComponent)
                //.recommendFragmentModule(new RecommendFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showRecommendList(List<Recommend.RecommendBooks> list) {
        mList.clear();
        mList.addAll(list);
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }
}

package com.justwayward.booker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.justwayward.booker.R;
import com.justwayward.booker.base.BaseActivity;
import com.justwayward.booker.component.AppComponent;
import com.justwayward.booker.component.DaggerMainActivityComponent;
import com.justwayward.booker.ui.contract.MainContract;
import com.justwayward.booker.ui.fragment.RecommendFragment;
import com.justwayward.booker.ui.presenter.MainActivityPresenter;
import com.justwayward.booker.view.RVPIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Bind(R.id.common_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.indicator)
    RVPIndicator mIndicator;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private List<Fragment> mTabContents;
    private FragmentPagerAdapter mAdapter;
    private List<String> mDatas;

    @Inject
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        configViews();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        setTitle("");
    }

    @Override
    public void initDatas() {
        mDatas = Arrays.asList("记任务", "执行中", "已完成");
        mTabContents = new ArrayList<>();

        for (String data : mDatas) {
           RecommendFragment fragment = new RecommendFragment();
            mTabContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    @Override
    public void configViews() {

        // 设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        // 设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;//super.onOptionsItemSelected(item);
    }

}
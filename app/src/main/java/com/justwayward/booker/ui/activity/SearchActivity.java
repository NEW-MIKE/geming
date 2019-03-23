package com.justwayward.booker.ui.activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.justwayward.booker.R;
import com.justwayward.booker.base.BaseActivity;
import com.justwayward.booker.bean.SearchDetail;
import com.justwayward.booker.component.AppComponent;
import com.justwayward.booker.ui.adapter.AutoCompleteAdapter;
import com.justwayward.booker.ui.adapter.SearchResultAdapter;
import com.justwayward.booker.ui.contract.SearchContract;
import com.justwayward.booker.ui.presenter.SearchPresenter;
import com.justwayward.booker.view.TagGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/8/6.
 */
public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @Override
    public void showHotWordList(List<String> list) {

    }

    @Override
    public void showAutoCompleteList(List<String> list) {

    }

    @Override
    public void showSearchResultList(List<SearchDetail.SearchBooks> list) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            return true;
    }
}

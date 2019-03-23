package com.justwayward.booker.ui.presenter;

import android.content.Context;

import com.justwayward.booker.api.BookApi;
import com.justwayward.booker.ui.contract.MainContract;

import javax.inject.Inject;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
public class MainActivityPresenter implements MainContract.Presenter<MainContract.View> {

    private Context context;
    private BookApi bookApi;

    private MainContract.View view;

    @Inject
    public MainActivityPresenter(Context context, BookApi bookApi) {
        this.context = context;
        this.bookApi = bookApi;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }
}

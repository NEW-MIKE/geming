package com.justwayward.booker.ui.contract;

/**
 * @author yuyh.
 * @date 16/8/6.
 */
public interface BaseContract {

    interface BasePresenter<T> {
        void attachView(T view);
    }

}

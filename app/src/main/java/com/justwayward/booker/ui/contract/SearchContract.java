package com.justwayward.booker.ui.contract;

import com.justwayward.booker.bean.SearchDetail;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface SearchContract {

    interface View {
        void showHotWordList(List<String> list);
        void showAutoCompleteList(List<String> list);
        void showSearchResultList(List<SearchDetail.SearchBooks> list);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getHotWordList();
        void getAutoCompleteList(String query);
        void getSearchResultList(String query);
    }

}

package com.justwayward.booker.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.justwayward.booker.R;
import com.justwayward.booker.base.Constant;
import com.justwayward.booker.bean.SearchDetail;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @author lfh.
 * @date 16/8/6.
 */
public class SearchResultAdapter extends EasyRVAdapter<SearchDetail.SearchBooks> {

    public SearchResultAdapter(Context context, List<SearchDetail.SearchBooks> list) {
        super(context, list, R.layout.item_search_result_list);
    }

    @Override
    protected void onBindData(EasyRVHolder holder, int position, SearchDetail.SearchBooks item) {
        ImageView ivCover = holder.getView(R.id.ivBookCover);
        Glide.with(mContext).load(Constant.IMG_BASE_URL + item.cover).into(ivCover);

        holder.setText(R.id.tvBookTitle, item.title)
                .setText(R.id.tvLatelyFollower, item.latelyFollower+"人在追 | ")
                .setText(R.id.tvRetentionRatio, (TextUtils.isEmpty(item.retentionRatio)?"0":item.retentionRatio) +"%读者留存 | ")
                .setText(R.id.tvAuthor, item.author+"著");
    }
}

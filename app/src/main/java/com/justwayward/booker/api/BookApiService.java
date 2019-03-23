package com.justwayward.booker.api;


import com.justwayward.booker.bean.AutoComplete;
import com.justwayward.booker.bean.BookDetail;
import com.justwayward.booker.bean.BookRead;
import com.justwayward.booker.bean.BooksByTag;
import com.justwayward.booker.bean.ChapterRead;
import com.justwayward.booker.bean.HotReview;
import com.justwayward.booker.bean.HotWord;
import com.justwayward.booker.bean.PostCount;
import com.justwayward.booker.bean.Recommend;
import com.justwayward.booker.bean.RecommendBookList;
import com.justwayward.booker.bean.SearchDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BookApiService {

    // 测试提交
    @GET("/book/recommend")
    Observable<Recommend> getRecomend(@Query("gender") String gender);

    @GET("/mix-toc/{bookId}")
    Observable<BookRead> getBookRead(@Path("bookId") String bookId);

    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead> getChapterRead(@Path("url") String url);

    @GET("/post/post-count-by-book")
    Observable<PostCount> postCountByBook(@Query("bookId") String bookId);

    @GET("/post/total-count")
    Observable<PostCount> postTotalCount(@Query("books") String bookId);

    @GET("/book/hot-word")
    Observable<HotWord> getHotWord();

    @GET("/book/auto-complete")
    Observable<AutoComplete> autoComplete(@Query("query") String query);

    @GET("/book/fuzzy-search")
    Observable<SearchDetail> searchBooks(@Query("query") String query);

    @GET("/post/review/best-by-book")
    Observable<HotReview> getHotReview(@Query("book") String book);

    @GET("/book-list/{bookId}/recommend")
    Observable<RecommendBookList> getRecommendBookList(@Path("bookId") String bookId, @Query("limit") String limit);

    @GET("/book/{bookId}")
    Observable<BookDetail> getBookDetail(@Path("bookId") String bookId);

    @GET("/book/by-tags")
    Observable<BooksByTag> getBooksByTag(@Query("tags") String tags, @Query("start") String start, @Query("limit") String limit);
}
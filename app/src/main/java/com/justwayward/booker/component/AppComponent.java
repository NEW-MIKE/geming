package com.justwayward.booker.component;

import android.content.Context;

import com.justwayward.booker.api.BookApi;
import com.justwayward.booker.module.AppModule;
import com.justwayward.booker.module.BookApiModule;

import dagger.Component;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
@Component(modules = {AppModule.class, BookApiModule.class})
public interface AppComponent {

    Context getContext();

    BookApi getReaderApi();

}
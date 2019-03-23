package com.justwayward.booker.component;

import com.justwayward.booker.ui.activity.MainActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface MainActivityComponent {
    MainActivity inject(MainActivity mainActivity);
}
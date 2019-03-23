package com.justwayward.booker.component;

import com.justwayward.booker.ui.activity.SearchActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface SearchActivityComponent {
    SearchActivity inject(SearchActivity searchActivity);
}
package com.students.di;

import com.students.view.activity.ListActivity;

import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {
    void inject(ListActivity listActivity);
}

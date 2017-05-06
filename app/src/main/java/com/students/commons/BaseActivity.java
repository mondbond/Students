package com.students.commons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.students.App;
import com.students.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.getAppComponent());
    }

    public abstract void setupComponent(AppComponent appComponent);
}

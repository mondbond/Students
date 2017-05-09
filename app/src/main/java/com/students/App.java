package com.students;

import android.app.Application;
import com.students.di.AppComponent;
import com.students.di.AppModule;
import com.students.di.DaggerAppComponent;

public class App extends Application{

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public void buildGraphAndInject() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        sAppComponent.inject(this);
    }
}

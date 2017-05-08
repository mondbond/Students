package com.students.di;

import com.students.App;
import com.students.model.DbHelper;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    DbHelper providesDbModel() {
        return new DbHelper(App.getAppComponent().getContext());
    }
}

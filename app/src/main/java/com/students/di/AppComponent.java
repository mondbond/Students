package com.students.di;

import android.content.Context;
import com.students.App;
import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    Context getContext();
}

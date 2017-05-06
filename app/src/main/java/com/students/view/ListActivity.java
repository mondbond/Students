package com.students.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.students.App;
import com.students.R;
import com.students.commons.BaseActivity;
import com.students.commons.IHasComponent;
import com.students.di.AppComponent;
import com.students.di.DaggerMainComponent;
import com.students.di.MainComponent;

public class ListActivity extends BaseActivity implements IHasComponent<MainComponent> {

    private static MainComponent sComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }


    @Override
    public void setupComponent(AppComponent appComponent) {
        sComponent = DaggerMainComponent.builder()
                .appComponent(App.getAppComponent())
                .build();
        sComponent.inject(this);
    }

    @Override
    public MainComponent getComponent() {
        return sComponent;
    }
}

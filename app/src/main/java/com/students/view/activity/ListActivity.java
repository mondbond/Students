package com.students.view.activity;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.students.App;
import com.students.R;
import com.students.commons.BaseActivity;
import com.students.commons.IHasComponent;
import com.students.di.AppComponent;
import com.students.di.DaggerMainComponent;
import com.students.di.MainComponent;
import com.students.view.fragments.StudentsFragment;

public class ListActivity extends BaseActivity implements IHasComponent<MainComponent>,
        StudentsFragment.OnFragmentInteractionListener {

    private static MainComponent sComponent;
    private StudentsFragment mStudentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if(mStudentsFragment == null){
            mStudentsFragment = new StudentsFragment();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.listFragmentContainer, mStudentsFragment);
        ft.commit();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void startEditor(long studentId) {

    }
}

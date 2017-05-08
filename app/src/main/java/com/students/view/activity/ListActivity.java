package com.students.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import com.students.App;
import com.students.R;
import com.students.commons.BaseActivity;
import com.students.commons.IHasComponent;
import com.students.di.AppComponent;
import com.students.di.DaggerMainComponent;
import com.students.di.MainComponent;
import com.students.view.fragments.EditFragment;
import com.students.view.fragments.StudentsFragment;

public class ListActivity extends BaseActivity implements IHasComponent<MainComponent>,
        StudentsFragment.OnFragmentInteractionListener {

    private static MainComponent sComponent;
    private StudentsFragment mStudentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        mStudentsFragment = (StudentsFragment) getSupportFragmentManager()
                .findFragmentByTag(StudentsFragment.STUDENTS_FRAGMENT_TAG);

        if(mStudentsFragment == null){
            mStudentsFragment = new StudentsFragment();
        }
        ft.replace(R.id.studentsFragmentContainer, mStudentsFragment,
                StudentsFragment.STUDENTS_FRAGMENT_TAG).commit();
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

    public static MainComponent getMainComponent() {
        return sComponent;
    }

    @Override
    public void startEditor(Long studentId) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(EditFragment.STUDENT_ID, studentId);
        startActivity(intent);
    }
}

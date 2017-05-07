package com.students.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.students.R;
import com.students.commons.IHasComponent;
import com.students.di.MainComponent;
import com.students.view.fragments.EditlFragment;

public class EditActivity extends AppCompatActivity implements IHasComponent<MainComponent> {

    private static MainComponent sComponent;
    private EditlFragment mEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        sComponent = ListActivity.getMainComponent();
        sComponent.inject(this);

        Intent intent = getIntent();
        Long d = intent.getLongExtra(EditlFragment.STUDENT_ID, 0);

        if(mEditFragment == null){
            mEditFragment = EditlFragment.newInstance(d);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.editFragmentContainer, mEditFragment);
        ft.commit();
    }

    @Override
    public MainComponent getComponent() {
        return sComponent;
    }
}

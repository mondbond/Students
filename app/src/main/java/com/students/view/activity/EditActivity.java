package com.students.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.students.R;
import com.students.commons.IHasComponent;
import com.students.di.MainComponent;
import com.students.view.fragments.EditFragment;

public class EditActivity extends AppCompatActivity implements IHasComponent<MainComponent> {

    private static MainComponent sComponent;
    private EditFragment mEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        sComponent = ListActivity.getMainComponent();
        sComponent.inject(this);

        Intent intent = getIntent();
        Long studentId = intent.getLongExtra(EditFragment.STUDENT_ID, 0);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mEditFragment = (EditFragment) getSupportFragmentManager()
                .findFragmentByTag(EditFragment.EDIT_FRAGMENT_TAG);

        if(mEditFragment == null){
            mEditFragment = EditFragment.newInstance(studentId);
        }
        ft.replace(R.id.editFragmentContainer, mEditFragment,
                EditFragment.EDIT_FRAGMENT_TAG).commit();
    }

    @Override
    public MainComponent getComponent() {
        return sComponent;
    }
}

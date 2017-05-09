package com.students.presenters;

import android.content.Context;
import com.students.R;
import com.students.commons.BasePresenter;
import com.students.model.DbHelper;
import com.students.model.db.Student;
import com.students.view.EditView;
import javax.inject.Inject;

public class EditPresenter implements BasePresenter<EditView> {

    private EditView mView;
    private DbHelper mDbHelper;
    private Context mContext;

    @Inject
    public EditPresenter(DbHelper mDbHelper, Context mContext) {
        this.mDbHelper = mDbHelper;
        this.mContext = mContext;
    }

    @Override
    public void init(EditView view) {
        this.mView = view;
    }

    public void getStudentById(long studentId) {
        mView.setDbQueryStatus(true);
        mDbHelper.getStudentById(studentId).subscribe(student -> {
            mView.setStudentInfo(student);
        });
    }

    public void addOrUpdateStudent(Student student) {
        mView.setDbQueryStatus(true);
        mDbHelper.updateOrInsertStudentData(student).subscribe(res -> {
            if(res){
                mView.showMessageInfo(mContext.getResources().getString(R.string.saved));
            }
        });
    }
}

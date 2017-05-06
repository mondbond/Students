package com.students.presenters;

import android.content.Context;

import com.students.R;
import com.students.commons.BasePresenter;
import com.students.model.DbHelper;
import com.students.model.db.Student;
import com.students.view.DetailView;
import javax.inject.Inject;

public class DetailPresenter implements BasePresenter<DetailView> {

    private DetailView mView;
    private DbHelper mDbHelper;
    private Context mContext;

    @Inject
    public DetailPresenter(DbHelper mDbHelper, Context mContext) {
        this.mDbHelper = mDbHelper;
        this.mContext = mContext;
    }

    @Override
    public void init(DetailView view) {
        this.mView = view;
    }

    public void getStudentById(long studentId) {
        mDbHelper.getStudentById(studentId).subscribe(student -> mView.setStudentInfo(student));
    }

    public void addOrUpdateStudent(Student student) {
        mDbHelper.updateOrInsertStudentData(student).subscribe(res -> {
            if(res){
                mView.showMessageInfo(mContext.getResources().getString(R.string.saved));
            }
        });
    }
}

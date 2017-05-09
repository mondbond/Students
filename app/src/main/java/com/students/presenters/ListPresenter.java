package com.students.presenters;

import android.content.Context;
import com.students.commons.BasePresenter;
import com.students.model.DbHelper;
import com.students.view.ListView;
import javax.inject.Inject;

public class ListPresenter implements BasePresenter<ListView> {

    private ListView mView;
    private DbHelper mDbHelper;

    @Inject
    public ListPresenter(DbHelper mDbHelper) {
        this.mDbHelper = mDbHelper;
    }

    @Override
    public void init(ListView view) {
        this.mView = view;
    }

    public void getAllStudents() {
        mView.setDbQueryStatus(true);
        mDbHelper.getAllStudents().subscribe(students -> {
            mView.setAllStudents(students);
        });
    }

    public void deleteAll() {
        mView.setDbQueryStatus(true);
        mDbHelper.deleteAll().subscribe(res -> {
            if(res){
                mView.setAllStudents(null);
            }
        });
    }

    public void deleteStudentById(long studentId) {
        mView.setDbQueryStatus(true);
        mDbHelper.deleteStudentById(studentId).subscribe(res -> {
            if(res){
                getAllStudents();
            }
        });
    }
}

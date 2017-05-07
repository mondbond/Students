package com.students.presenters;

import android.content.Context;
import android.util.Log;

import com.students.R;
import com.students.commons.BasePresenter;
import com.students.model.DbHelper;
import com.students.model.db.Student;
import com.students.view.ListView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class ListPresenter implements BasePresenter<ListView> {

    private ListView mView;
    private DbHelper mDbHelper;
    private Context mContext;

    @Inject
    public ListPresenter(DbHelper mDbHelper, Context mContext) {
        this.mDbHelper = mDbHelper;
        this.mContext = mContext;
    }

    @Override
    public void init(ListView view) {
        this.mView = view;
    }

    public void getAllStudents() {
        mDbHelper.getAllStudents().subscribe(students -> mView.setAllStudents(students));
    }

    public void deleteAll() {
        mDbHelper.deleteAll().subscribe(res -> {
            if(res){
                mView.setAllStudents(null);
            }
        });
    }

    public void deleteStudentById(long studentId) {
        mDbHelper.deleteStudentById(studentId).subscribe(res -> {
            if(res){
                getAllStudents();
            }
        });
    }
}

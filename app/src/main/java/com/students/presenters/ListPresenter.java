package com.students.presenters;

import com.students.commons.BasePresenter;
import com.students.model.DbHelper;
import com.students.view.ListView;
import javax.inject.Inject;
import rx.Subscription;

public class ListPresenter implements BasePresenter<ListView> {

    private ListView mView;
    private DbHelper mDbHelper;
    private Subscription mSubscription;

    @Inject
    public ListPresenter(DbHelper mDbHelper) {
        this.mDbHelper = mDbHelper;
    }

    @Override
    public void init(ListView view) {
        this.mView = view;
    }

    @Override
    public void unsubscribe() {
        if(mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void getAllStudents() {
        mView.setDbQueryStatus(true);
        mSubscription = mDbHelper.getAllStudents().subscribe(students -> {
            mView.setAllStudents(students);
        });
    }

    public void deleteAll() {
        mView.setDbQueryStatus(true);
        mSubscription = mDbHelper.deleteAll().subscribe(res -> {
            if(res){
                mView.setAllStudents(null);
            }
        });
    }

    public void deleteStudentById(long studentId) {
        mView.setDbQueryStatus(true);
        mSubscription = mDbHelper.deleteStudentById(studentId).subscribe(res -> {
            if(res){
                getAllStudents();
            }
        });
    }
}

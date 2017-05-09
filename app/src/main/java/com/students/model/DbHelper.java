package com.students.model;

import android.content.Context;
import com.students.model.db.DaoMaster;
import com.students.model.db.DaoSession;
import com.students.model.db.Student;
import com.students.model.db.StudentDao;
import org.greenrobot.greendao.database.Database;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DbHelper {

    private Database mDb;
    private DaoMaster mDaoMaster;

    public DbHelper(Context context) {
        this.mDb = new DaoMaster.DevOpenHelper(context, "students", null).getWritableDb();
        mDaoMaster = new DaoMaster(mDb);
    }

    public Observable<List<Student>> getAllStudents () {
        return Observable.create(new Observable.OnSubscribe<List<Student>>() {
            @Override
            public void call(Subscriber<? super List<Student>> subscriber) {

                DaoSession daoSession = mDaoMaster.newSession();
                StudentDao storeDao = daoSession.getStudentDao();
                List<Student> allStudents = new ArrayList<Student>();
                allStudents = storeDao.loadAll();

                subscriber.onNext(allStudents);
            }}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Student> getStudentById (long studentId) {
        return Observable.create(new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                StudentDao storeDao = daoSession.getStudentDao();
                Student student = storeDao.queryBuilder()
                        .where(StudentDao.Properties.Id.eq(studentId)).unique();

                subscriber.onNext(student);
            }}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> deleteStudentById (long studentId) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                StudentDao storeDao = daoSession.getStudentDao();
                storeDao.deleteByKey(studentId);

                subscriber.onNext(true);
            }}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> deleteAll () {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                StudentDao storeDao = daoSession.getStudentDao();
                storeDao.deleteAll();

                subscriber.onNext(true);
            }}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> updateOrInsertStudentData (Student newStudentData) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                DaoSession daoSession = mDaoMaster.newSession();
                StudentDao storeDao = daoSession.getStudentDao();
                storeDao.insertOrReplaceInTx(newStudentData);

                subscriber.onNext(true);
            }}).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

package com.students.view;

import com.students.model.db.Student;

public interface EditView {

    void setStudentInfo(Student student);
    void showMessageInfo(String msg);
    void setDbQueryStatus(boolean status);
}

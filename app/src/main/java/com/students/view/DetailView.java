package com.students.view;

import com.students.model.db.Student;

public interface DetailView {
    void setStudentInfo(Student student);
    void showMessageInfo(String msg);
}

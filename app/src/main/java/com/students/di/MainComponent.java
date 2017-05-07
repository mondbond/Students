package com.students.di;

import com.students.view.activity.ListActivity;
import com.students.view.fragments.StudentsFragment;

import dagger.Component;

@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {
    void inject(ListActivity listActivity);
    void inject(StudentsFragment studentsFragment);
}

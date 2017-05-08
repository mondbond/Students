package com.students;

import android.app.Application;

import com.students.di.AppComponent;
import com.students.di.AppModule;
import com.students.di.DaggerAppComponent;


public class App extends Application{

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public void buildGraphAndInject() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        sAppComponent.inject(this);


    }
}


/**
 *
 * <TextView
 android:id="@+id/detail_fragment_name"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="0"
 android:layout_row="0"/>


 <TextView
 android:id="@+id/detail_fragment_second_name"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="0"
 android:layout_row="1"/>


 <TextView
 android:id="@+id/detail_fragment_course"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="0"
 android:layout_row="2"/>


 <TextView
 android:id="@+id/detail_fragment_occupation"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="0"
 android:layout_row="3"/>


 <TextView
 android:id="@+id/detail_fragment_results"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="0"
 android:layout_row="4"/>


 <EditText
 android:id="@+id/detail_fragment_name_editor"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="1"
 android:layout_row="0"/>

 <EditText
 android:id="@+id/detail_fragment_second_name_editor"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="1"
 android:layout_row="1"/>

 <EditText
 android:id="@+id/detail_fragment_course_editor"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:inputType="number"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="1"
 android:layout_row="2"/>

 <EditText
 android:id="@+id/detail_fragment_occupation_editor"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="1"
 android:layout_row="3"/>

 <EditText
 android:id="@+id/detail_fragment_result_editor"
 android:layout_width="wrap_content"
 android:layout_height="20dp"
 android:inputType="number"
 android:text="Varlamov.ru"
 android:textColor="@color/colorText"
 android:textSize="18sp"
 android:layout_column="1"
 android:layout_row="4"/>
 *
 *
 *
 */

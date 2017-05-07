package com.students.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.students.R;
import com.students.commons.BaseFragment;
import com.students.di.MainComponent;
import com.students.model.db.Student;
import com.students.presenters.EditPresenter;
import com.students.view.EditView;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditlFragment extends BaseFragment implements EditView{

    public static final String STUDENT_ID = "studentId";
    public static final String OPERATION_TYPE = "operationType";

    public static final String ADD_OPERATION = "addOperation";
    public static final String EDIT_OPERATION = "editOpreration";

    private Long mStudentId;
    private String mOperationType;

    private Button mSaveButton;
    private EditText mId;
    private EditText mName;
    private EditText mSecondName;
    private EditText mOccupation;
    private EditText mResult;
    private EditText mCourse;

    @Inject
    EditPresenter mPresenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param studentId Parameter 1.
     * @return A new instance of fragment EditlFragment.
     */
    public static EditlFragment newInstance(Long studentId) {
        EditlFragment fragment = new EditlFragment();
        Bundle args = new Bundle();
        args.putLong(STUDENT_ID, studentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mStudentId = getArguments().getLong(STUDENT_ID);
            getComponent(MainComponent.class).inject(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        mPresenter.init(this);

        mId = (EditText) v.findViewById(R.id.detail_fragment_id_editor);
        mName = (EditText) v.findViewById(R.id.detail_fragment_name_editor);
        mSecondName = (EditText) v.findViewById(R.id.detail_fragment_second_name_editor);
        mCourse = (EditText) v.findViewById(R.id.detail_fragment_course_editor);
        mOccupation = (EditText) v.findViewById(R.id.detail_fragment_occupation_editor);
        mResult = (EditText) v.findViewById(R.id.detail_fragment_result_editor);

        mSaveButton = (Button) v.findViewById(R.id.detail_fragment_save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student newStudentInfo = new Student(Long.parseLong(mId.getText().toString()), mName.getText().toString(),
                        mSecondName.getText().toString(), Integer.parseInt(mCourse.getText().toString()),
                        mOccupation.getText().toString(), Integer.parseInt(mResult.getText().toString()));

                mPresenter.addOrUpdateStudent(newStudentInfo);
            }
        });

        if(mStudentId != 0){
            mPresenter.getStudentById(mStudentId);
        }

        return v;
    }


    @Override
    public void setStudentInfo(Student student) {
        mId.setText(String.valueOf(student.getId()));
        mName.setText(student.getName());
        mOccupation.setText(student.getOccupation());
        mSecondName.setText(student.getSecondName());
        mCourse.setText(Integer.toString(student.getCourse()));
        mResult.setText(Integer.toString(student.getResults()));
    }

    @Override
    public void showMessageInfo(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}

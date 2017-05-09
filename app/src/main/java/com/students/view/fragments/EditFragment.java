package com.students.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
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
import com.students.util.MinMaxInputFilter;
import com.students.view.EditView;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends BaseFragment implements EditView{

    public static final String EDIT_FRAGMENT_TAG = "editFragment";
    public static final String STUDENT_ID = "studentId";

    private Long mStudentId;
    private Button mSaveButton;
    private EditText mId;
    private EditText mName;
    private EditText mSurname;
    private EditText mOccupation;
    private EditText mResult;
    private EditText mCourse;
    private boolean mDbQueryState;

    @Inject
    EditPresenter mPresenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param studentId Parameter 1.
     * @return A new instance of fragment EditFragment.
     */
    public static EditFragment newInstance(Long studentId) {
        EditFragment fragment = new EditFragment();
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
        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        mPresenter.init(this);

        mId = (EditText) v.findViewById(R.id.edit_fragment_id_editor);
        mId.setFilters(new InputFilter[] {new MinMaxInputFilter(1, 999999)});
        mName = (EditText) v.findViewById(R.id.edit_fragment_name_editor);
        mSurname = (EditText) v.findViewById(R.id.edit_fragment_surname_editor);
        mCourse = (EditText) v.findViewById(R.id.edit_fragment_course_editor);
        mCourse.setFilters(new InputFilter[] {new MinMaxInputFilter(1, 6)});
        mOccupation = (EditText) v.findViewById(R.id.edit_fragment_occupation_editor);
        mResult = (EditText) v.findViewById(R.id.edit_fragment_result_editor);
        mResult.setFilters(new InputFilter[] {new MinMaxInputFilter(0, 100)});

        mSaveButton = (Button) v.findViewById(R.id.edit_fragment_save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mId.getText().toString().equals("") && !mName.getText().toString().equals("")
                        && !mSurname.getText().toString().equals("") && !mResult.getText().toString().equals("")
                        && !mCourse.getText().toString().equals("") && !mOccupation.getText().toString().equals("")) {
                    Student newStudentInfo = new Student(Long.parseLong(mId.getText().toString()), mName.getText().toString(),
                            mSurname.getText().toString(), Integer.parseInt(mCourse.getText().toString()),
                            mOccupation.getText().toString(), Integer.parseInt(mResult.getText().toString()));

                    if(!mDbQueryState) mPresenter.addOrUpdateStudent(newStudentInfo);
                }else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.empty_fields_error_msg),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

//        if we need to edit students data
//        don't make query if last one doesn't come yet
//        make query only if all fields empty (for first time only)
        if(mStudentId != 0 && !mDbQueryState &&mId.getText().toString().equals("")
                && mName.getText().toString().equals("") && mSurname.getText().toString().equals("")
                && mResult.getText().toString().equals("")
                && mCourse.getText().toString().equals("") && mOccupation.getText().toString().equals("")){
            mPresenter.getStudentById(mStudentId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setStudentInfo(Student student) {
        mId.setText(String.valueOf(student.getId()));
        mName.setText(student.getName());
        mOccupation.setText(student.getOccupation());
        mSurname.setText(student.getSurname());
        mCourse.setText(String.valueOf(student.getCourse()));
        mResult.setText(String.valueOf(student.getResults()));

        mDbQueryState = false;
    }

    @Override
    public void showMessageInfo(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        mDbQueryState = false;
    }

    @Override
    public void setDbQueryStatus(boolean status) {
        mDbQueryState = status;
    }
}

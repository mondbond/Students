package com.students.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.students.R;
import com.students.commons.BaseFragment;
import com.students.di.MainComponent;
import com.students.model.db.Student;
import com.students.presenters.ListPresenter;
import com.students.view.ListView;
import com.students.view.adapter.StudentsAdapter;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ListFragment extends BaseFragment implements ListView, StudentsAdapter.OnInteractionListener {

    public static final String STUDENTS_FRAGMENT_TAG = "studentFragment";

    private OnFragmentInteractionListener mListener;
    @Inject
    ListPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private int mRecyclerLastScrollPosition = 0;
    private StudentsAdapter mAdapter;
    private boolean mBdQueryState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.list_fragment_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.list_fragment_recycler_student_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new StudentsAdapter(null, this);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.init(this);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_fragment_menu, menu);
        MenuItem addItem = menu.findItem(R.id.add);
        addItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                give null or zero if we wont to add new student
                mListener.startEditor(null);
                return false;
            }
        });

        MenuItem deleteItem = menu.findItem(R.id.delete_all);
        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mPresenter.deleteAll();
                return false;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
//        don't make query if last one doesn't come yet
        if(!mBdQueryState) {
            mPresenter.getAllStudents();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mRecyclerLastScrollPosition = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                .findFirstCompletelyVisibleItemPosition();
    }

    @Override
    public void setAllStudents(List<Student> students) {
        mAdapter.setStudents(students);
        mAdapter.notifyDataSetChanged();
        if(mAdapter.getStudents() != null && !mAdapter.getStudents().isEmpty()) {
            if (mRecyclerLastScrollPosition < 0) mRecyclerLastScrollPosition = 0;
            mRecyclerView.smoothScrollToPosition(mRecyclerLastScrollPosition);
        }
        mBdQueryState = false;
    }

    @Override
    public void setDbQueryStatus(boolean status) {
        mBdQueryState = status;
    }

    @Override
    public void onItemClick(long studentId) {
        mListener.startEditor(studentId);
    }

    @Override
    public void onDeleteItemClick(long studentId) {
        mPresenter.deleteStudentById(studentId);
    }

    public interface OnFragmentInteractionListener {
//        give id if we wont to edit student data
        void startEditor(Long studentId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

package com.students.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
 * {@link StudentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentsFragment extends BaseFragment implements ListView, StudentsAdapter.OnIteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    ListPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private StudentsAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentsFragment newInstance(String param1, String param2) {
        StudentsFragment fragment = new StudentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        mPresenter.init(this);


        mPresenter.getAllStudents();
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
                Log.d("MENU_LIST", "add");
                return false;
            }
        });

        MenuItem deleteItem = menu.findItem(R.id.delete_all);
        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.d("MENU_LIST", "delete all");
                return false;
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void setAllStudents(List<Student> students) {
        Student student = new Student((long) 3242, "Ivan", "Berezovsky", 3, "удача", 200);
        students.add(student);

        mAdapter = new StudentsAdapter(students, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(long studentId) {
        mListener.startEditor(studentId);
    }

    @Override
    public void onDeleteItemClick(long studentId) {
        mPresenter.deleteStudentById(studentId);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void startEditor(long studentId);
    }
}

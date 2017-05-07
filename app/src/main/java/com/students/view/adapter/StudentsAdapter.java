package com.students.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.students.R;
import com.students.model.db.Student;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder>{

    private List<Student> mStudents;
    private OnIteractionListener mListener;

    public StudentsAdapter(List<Student> mStudents, OnIteractionListener listener) {
        this.mStudents = mStudents;
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mSecondName;
        TextView mCourse;
        ImageView mDelete;

        ViewHolder(View v) {
            super(v);

            mName = (TextView) v.findViewById(R.id.student_item_name);
            mSecondName = (TextView) v.findViewById(R.id.student_item_second_name);
            mCourse = (TextView) v.findViewById(R.id.student_item_course_text);
            mDelete = (ImageView) v.findViewById(R.id.student_item_delete);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.itemView;

        holder.mName.setText(mStudents.get(position).getName());
        holder.mSecondName.setText(mStudents.get(position).getSecondName());
        holder.mCourse.setText(String.valueOf(mStudents.get(position).getCourse()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mStudents.get(position).getId());
            }
        });

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeleteItemClick(mStudents.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mStudents != null) {
            return mStudents.size();
        }else {
            return 0;
        }
    }

    public interface OnIteractionListener{
        void onItemClick(long studentId);
        void onDeleteItemClick(long studentId);
    }

    public void setStudents(List<Student> mStudents) {
        this.mStudents = mStudents;
    }
}

package com.students.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.students.R;
import com.students.custom_view.ExamResultView;
import com.students.model.db.Student;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder>{

    private List<Student> mStudents;
    private OnInteractionListener mListener;

    public StudentsAdapter(List<Student> mStudents, OnInteractionListener listener) {
        this.mStudents = mStudents;
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mSurname;
        TextView mCourse;
        ImageView mDelete;
        ExamResultView mExamResultView;

        ViewHolder(View v) {
            super(v);

            mName = (TextView) v.findViewById(R.id.student_item_name);
            mSurname = (TextView) v.findViewById(R.id.student_item_surname);
            mCourse = (TextView) v.findViewById(R.id.student_item_course_text);
            mDelete = (ImageView) v.findViewById(R.id.student_item_delete);
            mExamResultView = (ExamResultView) v.findViewById(R.id.student_item_exam_result);
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
        holder.mSurname.setText(mStudents.get(position).getSurname());
        holder.mCourse.setText(String.valueOf(mStudents.get(position).getCourse()));
        holder.mExamResultView.setResultPart(mStudents.get(position).getResults());

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

    public interface OnInteractionListener {
        void onItemClick(long studentId);
        void onDeleteItemClick(long studentId);
    }

    public void setStudents(List<Student> mStudents) {
        this.mStudents = mStudents;
    }

    public List<Student> getStudents() {
        return mStudents;
    }
}

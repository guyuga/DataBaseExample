package com.guyuga.databaseproject.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.Models.Student;
import com.guyuga.databaseproject.Presentor.StudentsContract;
import com.guyuga.databaseproject.R;
import com.guyuga.databaseproject.ViewModel.StudentRowViewModel;
import com.guyuga.databaseproject.databinding.StudentRowViewBinding;


import java.util.List;

/**
 * Created by guyug on 24/2/2017.
 */

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.BindingHolder>  {

    private List<Student> students;
    private StudentsContract.Presenter.OnItemClickListener listener;


    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public StudentRecyclerAdapter(List<Student> students, @NonNull StudentsContract.Presenter.OnItemClickListener listener) {
        this.students = students;
        this.listener = listener;
    }

    public StudentRecyclerAdapter(@NonNull StudentsContract.Presenter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public StudentRecyclerAdapter() {
    }

    @Override
    public StudentRecyclerAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StudentRowViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.student_row_view,
                parent,
                false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(StudentRecyclerAdapter.BindingHolder holder, int position) {
        StudentRowViewBinding mStudentRowViewBinding = holder.binding;
        mStudentRowViewBinding.setViewModel(new StudentRowViewModel(students.get(position)));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    public static class BindingHolder extends RecyclerView.ViewHolder {
        private StudentRowViewBinding binding;

        public BindingHolder(StudentRowViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

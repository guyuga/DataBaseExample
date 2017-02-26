package com.guyuga.databaseproject.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.R;
import com.guyuga.databaseproject.ViewModel.CourseRowViewModel;
import com.guyuga.databaseproject.databinding.CourseRowViewBinding;


import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 */

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.BindingHolder> {

    private List<Course> courses;
    private DataRepository mDataRepository;

    public void setCourses(List<Course> students) {
        this.courses = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public CourseRecyclerAdapter(List<Course> courses, @NonNull  DataRepository mDataRepository) {
        this.courses = courses;
        this.mDataRepository = mDataRepository;
    }

    public CourseRecyclerAdapter(@NonNull DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    public CourseRecyclerAdapter() {
    }

    @Override
    public CourseRecyclerAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CourseRowViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.course_row_view,
                parent,
                false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(CourseRecyclerAdapter.BindingHolder holder, int position) {
        CourseRowViewBinding mCourseRowViewBinding = holder.binding;
        mCourseRowViewBinding.setViewModel(new CourseRowViewModel(courses.get(position), mDataRepository));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public static class BindingHolder extends RecyclerView.ViewHolder {
        private CourseRowViewBinding binding;

        public BindingHolder(CourseRowViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

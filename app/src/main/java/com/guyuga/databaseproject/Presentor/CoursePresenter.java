package com.guyuga.databaseproject.Presentor;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 */

public class CoursePresenter implements CourseContract.Presenter, AbstractDataSource.LoadObjectCallback {

    private final CourseContract.View mCourseView;
    private final DataRepository mDataRepository;

    public CoursePresenter(@NonNull DataRepository mDataRepository, @NonNull CourseContract.View mCourseView) {
        this.mDataRepository = mDataRepository;
        this.mCourseView = mCourseView;
        mCourseView.setPresenter(this);
    }

    private void loadCourses() {

        mDataRepository.getCourses(new AbstractDataSource.LoadCoursesCallback() {
            @Override
            public void onLoadCourses(List<Course> courses) {
                mCourseView.updateRecycleView(courses);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void start() {

        loadCourses();
    }

    @Override
    public void stop() {

    }

    @Override
    public void onLoad() {
        loadCourses();
    }

    @Override
    public void onDataNotAvailable() {

    }
}

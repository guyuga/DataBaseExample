package com.guyuga.databaseproject.ViewModel;

import android.databinding.BaseObservable;

import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 */

public class CourseRowViewModel extends BaseObservable {

    private Course mCourse;
    private DataRepository mDataRepository;

    public CourseRowViewModel(Course mCourse, DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
        this.mCourse = mCourse;
    }

    public String getCourseName() {return mCourse.getName();}

    public String getCourseCredits() {return mCourse.getCredits();}

    public String getNumberOfStudents() {
        mDataRepository.getNumberOfStudentsInCourse(mCourse, new AbstractDataSource.LoadStudentsCallback() {
            @Override
            public void onLoadStudents(List<Student> students) {
                Course course = new Course(mCourse.getName(), mCourse.getCredits());
                course.setId(mCourse.getId());
                course.setNumberOfStudents(students.size());
                mDataRepository.updateCourse(course,null);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return String.valueOf(mCourse.getNumberOfStudents());
    }
}

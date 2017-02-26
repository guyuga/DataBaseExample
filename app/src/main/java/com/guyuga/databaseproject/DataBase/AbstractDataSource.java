package com.guyuga.databaseproject.DataBase;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;
import java.util.Objects;

/**
 * Created by guyug on 21/2/2017.
 * main entry point for all data source operations
 */

public interface AbstractDataSource {

    interface LoadStudentsCallback {

        void onLoadStudents(List<Student> students);

        void onDataNotAvailable();
    }

    interface LoadCoursesCallback {

        void onLoadCourses(List<Course> courses);

        void onDataNotAvailable();
    }

    interface ResultCallback {

        void onSuccess();

        void onFailure(Exception exception);

    }

    interface LoadObjectCallback {

        void onLoad();

        void onDataNotAvailable();
    }

    interface LoadNumberOfStudentsCallback {

        int onLoadStudents(List<Student> students);

        int onDataNotAvailable();
    }

    void getStudents(@NonNull LoadStudentsCallback callback);

    void getCourses(@NonNull LoadCoursesCallback callback);

    void saveStudents(List<Student> students, @NonNull ResultCallback callback);

    void saveStudent(Student student, @NonNull ResultCallback callback);

    void saveCourses(List<Course> courses, @NonNull ResultCallback callback);

    void getNumberOfStudentsInCourse(@NonNull Course course, @NonNull LoadStudentsCallback callback);

    void registerForStudentsUpdates(@NonNull LoadObjectCallback callback);

    void registerForCoursesUpdates(@NonNull LoadObjectCallback callback);

    void unRegisterForUpdate();

    void updateCourse(@NonNull Course course, @NonNull LoadCoursesCallback callback);

    void updateStudentCourse(@NonNull Student student, @NonNull int courseId, @NonNull boolean isSelected);

}

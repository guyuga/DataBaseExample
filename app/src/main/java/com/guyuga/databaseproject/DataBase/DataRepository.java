package com.guyuga.databaseproject.DataBase;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guyug on 22/2/2017.
 * Concrete repository implementation responsible for all data operations.
 */

public class DataRepository implements AbstractDataSource {

    private static DataRepository INSTANCE = null;

//    private final AbstractDataSource mTasksRemoteDataSource;

    private final AbstractDataSource mLocalDataSource;

    // Prevent direct instantiation.
    private DataRepository(@NonNull AbstractDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param localDataSource  the device storage data source
     * @return the {@link DataRepository} instance
     */
    public static DataRepository getInstance(AbstractDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getStudents(@NonNull LoadStudentsCallback callback) {
        mLocalDataSource.getStudents(callback);
    }

    @Override
    public void getCourses(@NonNull LoadCoursesCallback callback) {
        mLocalDataSource.getCourses(callback);
    }

    @Override
    public void saveStudents(List<Student> students, @NonNull ResultCallback callback) {
        mLocalDataSource.saveStudents(students, callback);
    }

    @Override
    public void saveStudent(Student student, @NonNull ResultCallback callback) {
        mLocalDataSource.saveStudent(student, callback);
    }

    @Override
    public void saveCourses(List<Course> courses, @NonNull ResultCallback callback) {
        mLocalDataSource.saveCourses(courses, callback);
    }

    @Override
    public void getNumberOfStudentsInCourse(@NonNull Course course, @NonNull LoadStudentsCallback callback) {
        mLocalDataSource.getNumberOfStudentsInCourse(course, callback);
    }

    @Override
    public void registerForStudentsUpdates(@NonNull LoadObjectCallback callback) {
        mLocalDataSource.registerForStudentsUpdates(callback);
    }

    @Override
    public void registerForCoursesUpdates(@NonNull LoadObjectCallback callback) {
        mLocalDataSource.registerForCoursesUpdates(callback);
    }

    @Override
    public void unRegisterForUpdate() {
        mLocalDataSource.unRegisterForUpdate();
    }

    @Override
    public void updateCourse(@NonNull Course course, @NonNull LoadCoursesCallback callback) {
        mLocalDataSource.updateCourse(course, callback);
    }

    @Override
    public void updateStudentCourse(@NonNull Student student, @NonNull int courseId, @NonNull boolean isSelected) {
        mLocalDataSource.updateStudentCourse(student, courseId, isSelected);
    }


}

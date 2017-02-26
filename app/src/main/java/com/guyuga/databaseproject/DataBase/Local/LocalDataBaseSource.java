package com.guyuga.databaseproject.DataBase.Local;

import android.content.Context;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guyug on 22/2/2017.
 * concrete class implementing local data base access
 */

public class LocalDataBaseSource implements AbstractDataSource {

    private static LocalDataBaseSource INSTANCE;
    private RealmChangeListener realmListener;
    private Realm realm;

    // Prevent direct instantiation.
    private LocalDataBaseSource(@NonNull Context context) {
        realm = Realm.getDefaultInstance();
    }

    public static LocalDataBaseSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataBaseSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getStudents(@NonNull LoadStudentsCallback callback) {
        RealmQuery<Student> query = realm.where(Student.class);
        if (query.isValid())
            callback.onLoadStudents(query.findAll());
        else
            callback.onDataNotAvailable();
    }

    @Override
    public void getCourses(@NonNull LoadCoursesCallback callback) {
        RealmQuery<Course> query = realm.where(Course.class);
        if (query.isValid())
            callback.onLoadCourses(query.findAll());
        else
            callback.onDataNotAvailable();
    }

    @Override
    public void saveStudents(final List<Student> students, @NonNull ResultCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Student.class);
                realm.copyToRealmOrUpdate(students);
            }
        });

    }

    @Override
    public void saveStudent(final Student student, @NonNull final ResultCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(student);
            }
        });
    }

    @Override
    public void saveCourses(final List<Course> courses, @NonNull ResultCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Course.class);
                int nextIndex = 0;
                for (Course course: courses) {
                    Number index = realm.where(Course.class).max("id");
                    if (index != null)
                        nextIndex = index.intValue() + 1;
                    course.setId(nextIndex);
                    realm.copyToRealmOrUpdate(course);
                }
            }
        });
    }

    @Override
    public void getNumberOfStudentsInCourse(@NonNull Course course, @NonNull LoadStudentsCallback callback) {

        RealmResults<Student> query = realm.where(Student.class)
                .equalTo("courses.id", course.getId())
                .findAll();
        if (query.isValid())
            callback.onLoadStudents(query);
        else
            callback.onDataNotAvailable();
    }

    @Override
    public void registerForStudentsUpdates(@NonNull final LoadObjectCallback callback) {

    }

    @Override
    public void registerForCoursesUpdates(@NonNull LoadObjectCallback callback) {

    }

    @Override
    public void unRegisterForUpdate() {

    }

    @Override
    public void updateCourse(@NonNull final Course course, @NonNull final LoadCoursesCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(course);
            }
        });
    }

    @Override
    public void updateStudentCourse(@NonNull Student student, @NonNull int courseId, @NonNull final boolean isSelected) {
        final Student results = realm.where(Student.class)
                .equalTo("name", student.getName())
                .findFirst();
        final Course courseResult = realm.where(Course.class)
                .equalTo("id",courseId)
                .findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (results.getCourses().contains(courseResult) && !isSelected){
                    results.getCourses().remove(courseResult);
                }else if (!results.getCourses().contains(courseResult) && isSelected){
                    results.getCourses().add(courseResult);
                }
            }
        });
    }
}

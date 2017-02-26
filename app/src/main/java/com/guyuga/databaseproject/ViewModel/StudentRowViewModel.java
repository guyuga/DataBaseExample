package com.guyuga.databaseproject.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Models.Student;

/**
 * Created by guyug on 23/2/2017.
 */

public class StudentRowViewModel extends BaseObservable {

    private Student mStudent;

    public StudentRowViewModel(Student student) {
        mStudent = student;
    }

    public String getStudentName() {return mStudent.getName();}

    public String getStudentBirthDate() {return mStudent.getBirthDate();}

    public String getNumberOfCourses() {
        return String.valueOf(mStudent.getCourses().size());
    }
}

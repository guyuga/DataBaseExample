package com.guyuga.databaseproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.guyuga.databaseproject.Adapter.LinkAdapter;
import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class LinkStudentActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener, CheckBox.OnCheckedChangeListener{

    private List<Student> mStudents;
    private List<Course> mCourses;
    private DataRepository mDataRepository;
    private ArrayAdapter<String> mSpinnerAdapter;
    private LinkAdapter mLinkAdapter;
    private Student mSelectedStudent;

    public LinkStudentActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataRepository = Injection.provideDataRepository(this);
        loadStudents();
        loadCourses();

        Spinner studentSpinner = (Spinner) findViewById(R.id.student_spinner);
        Button done = (Button) findViewById(R.id.add_button);
        done.setOnClickListener(this);
        ListView coursesList = (ListView) findViewById(R.id.course_list);

        List<String> studentNames = new ArrayList<String>();
        for (Student student: mStudents){
            studentNames.add(student.getName());
        }
        mSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, studentNames);
        studentSpinner.setAdapter(mSpinnerAdapter);
        mSelectedStudent = mStudents.get(0);
        studentSpinner.setSelection(0);
        studentSpinner.setOnItemSelectedListener(this);

        mLinkAdapter = new LinkAdapter(mCourses, mSelectedStudent, this);
        coursesList.setAdapter(mLinkAdapter);
    }

    public void loadStudents(){
        mDataRepository.getStudents(new AbstractDataSource.LoadStudentsCallback() {
            @Override
            public void onLoadStudents(List<Student> students) {
                mStudents = students;
                if (mLinkAdapter != null)
                    mLinkAdapter.notifyDataSetChanged();
                if (mSpinnerAdapter != null)
                    mSpinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    public void loadCourses(){
        mDataRepository.getCourses(new AbstractDataSource.LoadCoursesCallback() {
            @Override
            public void onLoadCourses(List<Course> courses) {
                mCourses = courses;
                if (mLinkAdapter != null)
                    mLinkAdapter.notifyDataSetChanged();
                if (mSpinnerAdapter != null)
                    mSpinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSelectedStudent = mStudents.get(i);
        if (mLinkAdapter != null) {
            mLinkAdapter.setmStudent(mSelectedStudent);
            mLinkAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mDataRepository.updateStudentCourse(mSelectedStudent, (Integer) compoundButton.getTag(), b);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}

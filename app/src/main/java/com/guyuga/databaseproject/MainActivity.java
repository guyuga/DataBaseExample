package com.guyuga.databaseproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Executer.UseCaseHandler;
import com.guyuga.databaseproject.Fragments.CourseFragment;
import com.guyuga.databaseproject.Fragments.StudentsFragment;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;
import com.guyuga.databaseproject.Presentor.CoursePresenter;
import com.guyuga.databaseproject.Presentor.StudentPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private UseCaseHandler mUseCaseHandler;
    private StudentsFragment mStudentFragment;
    private CourseFragment mCourseFragment;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mStudentFragment = StudentsFragment.newInstance();
        mCourseFragment = CourseFragment.newInstance();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LinkStudentActivity.class);
                startActivity(intent);
            }
        });

        // Set up DataBase initial items
        InitializeDataBaseData();

        // Create Presenters
        new StudentPresenter(Injection.provideDataRepository(this), mStudentFragment);
        new CoursePresenter(Injection.provideDataRepository(this), mCourseFragment);
    }

    /*
    * This function saves the initial Students and Courses to the Database.
     */
    private void InitializeDataBaseData() {

        DataRepository dataRepository = Injection.provideDataRepository(this);
        dataRepository.saveStudents(getInitialStudentList(), new AbstractDataSource.ResultCallback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onFailure(Exception exception) {
            }
        });

        dataRepository.saveCourses(getInitialCourseList(), new AbstractDataSource.ResultCallback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onFailure(Exception exception) {
            }
        });
    }

    private List<Student> getInitialStudentList() {

        List<Student> students = new ArrayList<>();

        students.add(new Student("John Smith", "12.3.1994"));
        students.add(new Student("Mark David", "6.5.1992"));
        students.add(new Student("Elton Don Beaver", "1.7.1991"));
        students.add(new Student("Ellen Daffy", "11.10.1990"));
        students.add(new Student("Emma Pone", "4.3.1993"));
        students.add(new Student("Carl Nick", "12.7.1992"));
        students.add(new Student("Erik John Carlson", "1.21.1991"));
        students.add(new Student("Pit Johnny", "12.24.1995"));

        return students;
    }

    private List<Course> getInitialCourseList() {

        List<Course> courses = new ArrayList<>();

        courses.add(new Course("Algebra", "3"));
        courses.add(new Course("Calculus", "4"));
        courses.add(new Course("C++", "4"));
        courses.add(new Course("Java", "4"));
        courses.add(new Course("Poetry", "2"));
        courses.add(new Course("Sport", "1"));
        courses.add(new Course("English", "3"));
        courses.add(new Course("Final Project", "5"));

        return courses;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.recycler_fragment, container, false);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return mStudentFragment;
                case 1:
                    return mCourseFragment;
                default:
                    return PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Students";
                case 1:
                    return "Courses";
            }
            return null;
        }
    }
}

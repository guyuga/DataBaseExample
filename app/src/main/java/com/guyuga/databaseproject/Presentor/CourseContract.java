package com.guyuga.databaseproject.Presentor;

import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;
import com.guyuga.databaseproject.Util.BasePresenter;
import com.guyuga.databaseproject.Util.BaseView;

import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 */

public interface CourseContract {

    interface View extends BaseView<CourseContract.Presenter> {

        void updateRecycleView(List<Course> courses);

    }

    interface Presenter extends BasePresenter {


    }
}

package com.guyuga.databaseproject.Presentor;

import com.guyuga.databaseproject.Models.Student;
import com.guyuga.databaseproject.Util.BasePresenter;
import com.guyuga.databaseproject.Util.BaseView;

import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 * This specifies the contract between the view and the presenter.
 */

public interface StudentsContract {


    interface View extends BaseView<Presenter> {

        void updateRecycleView(List<Student> students);

    }

    interface Presenter extends BasePresenter {

        interface OnItemClickListener {
            void onItemClickListener(int position);
        }
    }


}

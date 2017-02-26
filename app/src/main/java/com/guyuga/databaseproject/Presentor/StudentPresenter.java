package com.guyuga.databaseproject.Presentor;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 */

public class StudentPresenter implements StudentsContract.Presenter, AbstractDataSource.LoadObjectCallback {

    private final StudentsContract.View mStudentView;
    private final DataRepository mDataRepository;

    public StudentPresenter(@NonNull DataRepository mDataRepository, @NonNull StudentsContract.View mStudentView) {
        this.mDataRepository = mDataRepository;
        this.mStudentView = mStudentView;
        mStudentView.setPresenter(this);
    }

    private void loadStudents() {

        mDataRepository.getStudents(new AbstractDataSource.LoadStudentsCallback() {
            @Override
            public void onLoadStudents(List<Student> students) {
                mStudentView.updateRecycleView(students);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        /*GetStudents.RequestValues mRequestValue = new GetStudents.RequestValues();

        mUseCaseHandler.execute(mGetStudents, mRequestValue, new UseCase.UseCaseCallback<GetStudents.ResponseValue>() {
            @Override
            public void onSuccess(GetStudents.ResponseValue response) {
                mStudentView.updateRecycleView(response.getStudents());

            }

            @Override
            public void onError() {

            }
        });*/
    }

    private void RegisterForChange(){

    }

    @Override
    public void start() {

        loadStudents();
    }

    @Override
    public void stop() {

    }

    @Override
    public void onLoad() {
        loadStudents();
    }

    @Override
    public void onDataNotAvailable() {

    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClickListener(int position) {

        }
    };
}

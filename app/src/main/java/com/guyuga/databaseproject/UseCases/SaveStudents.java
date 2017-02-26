package com.guyuga.databaseproject.UseCases;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Executer.UseCase;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;

/**
 * Created by guyug on 24/2/2017.
 */

public class SaveStudents extends UseCase<SaveStudents.RequestValue, SaveStudents.ResponseValue> {

    private final DataRepository mDataRepository;

    public SaveStudents(@NonNull DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    public DataRepository getmDataRepository() {
        return mDataRepository;
    }

    @Override
    protected void executeUseCase(RequestValue requestValue) {

        mDataRepository.saveStudents(requestValue.getmStudents(), new AbstractDataSource.ResultCallback() {
            @Override
            public void onSuccess() {
                ResponseValue responseValue = new ResponseValue(true);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onFailure(Exception exception) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValue implements UseCase.RequestValue {

        private static List<Student> mStudents;

        public RequestValue(List<Student> students) {
            mStudents = students;

        }

        public List<Student> getmStudents() {
            return mStudents;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private static boolean success;

        public ResponseValue(boolean result) {
            success = result;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}

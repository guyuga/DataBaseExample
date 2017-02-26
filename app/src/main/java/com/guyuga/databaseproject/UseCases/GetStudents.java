package com.guyuga.databaseproject.UseCases;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.AbstractDataSource;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Executer.UseCase;
import com.guyuga.databaseproject.Models.Student;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guyug on 23/2/2017.
 * Get students use case implementation
 */

public class GetStudents extends UseCase<GetStudents.RequestValues, GetStudents.ResponseValue> {

    private final DataRepository mDataRepository;

    public GetStudents(@NonNull DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValue) {
        mDataRepository.getStudents(new AbstractDataSource.LoadStudentsCallback() {
            @Override
            public void onLoadStudents(List<Student> students) {
                ResponseValue responseValue = new ResponseValue(students);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValue {

        public RequestValues() {
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Student> students;

        public ResponseValue(List<Student> students) {
            this.students = students;
        }

        public List<Student> getStudents() {
            return students;
        }
    }
}

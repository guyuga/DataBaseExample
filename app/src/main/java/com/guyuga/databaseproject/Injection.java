package com.guyuga.databaseproject;

import android.content.Context;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.DataBase.Local.LocalDataBaseSource;
import com.guyuga.databaseproject.Executer.UseCaseHandler;
import com.guyuga.databaseproject.UseCases.GetStudents;
import com.guyuga.databaseproject.UseCases.SaveStudents;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guyug on 24/2/2017.
 * Enables injection of production implementations for
 * {@link DataRepository} at compile time.
 */

public class Injection {

    public static DataRepository provideDataRepository(@NonNull Context context) {
        return DataRepository.getInstance(LocalDataBaseSource.getInstance(context));
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static GetStudents provideGetStudents(@NonNull Context context) {
        return new GetStudents(provideDataRepository(context));
    }

    public static SaveStudents provideSaveStudents(Context context) {
        return new SaveStudents(Injection.provideDataRepository(context));
    }
}

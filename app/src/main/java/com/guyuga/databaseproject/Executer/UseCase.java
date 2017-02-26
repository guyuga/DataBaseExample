package com.guyuga.databaseproject.Executer;

/**
 * Created by guyug on 23/2/2017.
 */

public abstract class UseCase<Q extends UseCase.RequestValue, P extends UseCase.ResponseValue> {

    private Q mRequestValue;

    private UseCaseCallback<P> mUseCaseCallback;

    public Q getRequestValue() {
        return mRequestValue;
    }

    void setRequestValue(Q mRequestValue) {
        this.mRequestValue = mRequestValue;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    void setUseCaseCallback(UseCaseCallback<P> mUseCaseCallback) {
        this.mUseCaseCallback = mUseCaseCallback;
    }

    void run() {executeUseCase(mRequestValue);}

    protected abstract void executeUseCase(Q requestValue);

    public interface RequestValue {}

    public interface ResponseValue {}

    interface UseCaseCallback<R> {

        void onSuccess(R response);

        void onError();
    }
}

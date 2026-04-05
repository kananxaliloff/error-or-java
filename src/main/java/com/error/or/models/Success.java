package com.error.or.models;

public final class Success<T> extends ErrorOr<T> {
    private final T data;

    Success(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }
}

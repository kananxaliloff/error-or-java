package com.error.or.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public final class Failure<T> extends ErrorOr<T> {

    private final List<Error> errors;

    Failure(List<Error> errors) {
        if (errors == null || errors.isEmpty()) {
            throw new IllegalArgumentException("Failure must contain at least one error.");
        }

        this.errors = List.copyOf(errors);
    }

    public List<Error> getErrors() {
        return List.copyOf(errors);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isFailure() {
        return true;
    }
}

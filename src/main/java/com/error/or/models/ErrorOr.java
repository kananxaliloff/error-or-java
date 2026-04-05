package com.error.or.models;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public sealed abstract class ErrorOr<T> permits Success, Failure {
    public abstract boolean isSuccess();
    public abstract boolean isFailure();

    @SuppressWarnings("unchecked")
    public final <R> ErrorOr<R> map(Function<T, R> mapper) {
        if (this instanceof Success<T> s) {
            return ErrorOr.success(mapper.apply(s.getData()));
        }
        return (ErrorOr<R>) this;
    }

    @SuppressWarnings("unchecked")
    public final <R> ErrorOr<R> flatMap(Function<T, ErrorOr<R>> mapper) {
        if (this instanceof Success<T> s) {
            return mapper.apply(s.getData());
        }
        return (ErrorOr<R>) this;
    }

    public final ErrorOr<T> onSuccess(Consumer<T> action) {
        if (this instanceof Success<T> s) {
            action.accept(s.getData());
        }
        return this;
    }

    public final ErrorOr<T> onFailure(Consumer<List<Error>> action) {
        if (this instanceof Failure<T> s) {
            action.accept(s.getErrors());
        }
        return this;
    }

    public static <T> Success<T> success(T data) {
        return new Success<>(data);
    }

    public static <T> Failure<T> failure(Error error) {
        return new Failure<>(List.of(error));
    }

    public static <T> ErrorOr<T> failure(List<Error> errors) {
        return new Failure<>(errors);
    }
}

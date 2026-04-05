package com.error.or;

import com.error.or.models.ErrorOr;
import com.error.or.models.Failure;
import com.error.or.models.Success;
import org.junit.jupiter.api.Test;
import com.error.or.models.Error;
import static org.assertj.core.api.Assertions.assertThat;

class ErrorOrTest {

    @Test
    void shouldCreateSuccess() {
        ErrorOr<String> result = ErrorOr.success("Hello");

        assertThat(result.isSuccess()).isTrue();
        assertThat(((Success<String>) result).getData()).isEqualTo("Hello");
    }

    @Test
    void shouldCreateFailure() {
        Error error = new Error("404");
        Failure<String> result = ErrorOr.failure(error);

        assertThat(result.isFailure()).isTrue();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    void map_shouldTransformValue_onSuccess() {
        ErrorOr<Integer> result = ErrorOr.success(5)
                .map(i -> i * 2);

        assertThat(((Success<Integer>) result).getData()).isEqualTo(10);
    }

    @Test
    void map_shouldStayFailure_onFailure() {
        ErrorOr<Integer> result = ErrorOr.failure(new Error("E"));
        ErrorOr<String> mapped = result.map(Object::toString);

        assertThat(mapped.isFailure()).isTrue();
    }
}

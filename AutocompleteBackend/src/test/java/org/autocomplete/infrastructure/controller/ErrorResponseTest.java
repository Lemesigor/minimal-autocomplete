package org.autocomplete.infrastructure.controller;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ErrorResponseTest {
    @Test
    public void getStatusBasedOnError() {
        ErrorResponse errorResponse = new ErrorResponse("message", new IllegalArgumentException());
        assertThat(errorResponse.getStatus(), is(400));

        errorResponse = new ErrorResponse("message", new Exception());
        assertThat(errorResponse.getStatus(), is(500));
    }
}

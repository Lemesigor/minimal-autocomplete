package org.autocomplete.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueryTest {
    @Test
    public void createAValue() {
        final var query = Query.of("query", 5);
        assertThat(query.value(), is("query"));
    }

    @Test
    public void addDefaultLimitToValue() {
        final var query = Query.of("query");
        assertThat(query.value(), is("query"));
        assertThat(query.limit(), is(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenValueIsNull() {
        Query.of(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenValueIsEmpty() {
        Query.of("", 5);
    }

}

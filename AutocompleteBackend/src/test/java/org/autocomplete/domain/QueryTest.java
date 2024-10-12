package org.autocomplete.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueryTest {
    @Test
    public void createAQuery() {
        final var query = Query.of("query", 5);
        assertThat(query.query(), is("query"));
    }

    @Test
    public void addDefaultLimitToQuery() {
        final var query = Query.of("query");
        assertThat(query.query(), is("query"));
        assertThat(query.limit(), is(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenQueryIsNull() {
        Query.of(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenQueryIsEmpty() {
        Query.of("", 5);
    }
}

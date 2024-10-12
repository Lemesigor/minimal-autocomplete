package org.autocomplete.domain;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TermTest {
    @Test
    public void createATerm() {
        Term term = Term.of(1L, "term");

        assertThat(term.id(), is(1L));
        assertThat(term.value(), is("term"));
    }

    @Test
    public void createATermWithoutId() {
        Term term = Term.of("term");

        assertThat(term.id(), is((Long) null));
        assertThat(term.value(), is("term"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenValueIsNull() {
        Term.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenValueIsEmpty() {
        Term.of("");
    }
}

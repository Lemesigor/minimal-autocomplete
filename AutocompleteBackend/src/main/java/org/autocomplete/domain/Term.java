package org.autocomplete.domain;

import java.util.Objects;

public class Term {
    private final Long id;
    private final String value;

    private Term(Long id, String value) {
        this.id = id;
        this.value = value;
        validate();
    }

    public static Term of(Long id, String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return new Term(id, value);
    }

    public static Term of(String value) {
        return new Term(null, value);
    }

    private void validate() {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    public Long id() {
        return id;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        return equalsCasted((Term) other);


    }

    private boolean equalsCasted(Term other) {
        return Objects.equals(id, other.id)
                && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}

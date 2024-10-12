package org.autocomplete.domain;

import java.util.Objects;

public class Query {
    private static final int DEFAULT_LIMIT = 10;

    private final String value;
    private final int limit;

    private Query(String value, int limit) {
        this.value = value;
        this.limit = limit > 0 ? limit : DEFAULT_LIMIT;

        validate();
    }

    public static Query of(String query, int limit) {
        return new Query(query, limit);
    }

    public static Query of(String query) {
        return new Query(query, DEFAULT_LIMIT);
    }


    private void validate() {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Query cannot be null or empty");
        }
    }

    public String query() {
        return value;
    }

    public int limit() {
        return limit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        return equalsCasted((Query) other);


    }
    private boolean equalsCasted(Query other) {
        return Objects.equals(limit, other.limit)
                && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, limit);
    }
}

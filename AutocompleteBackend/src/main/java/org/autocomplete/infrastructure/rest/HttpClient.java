package org.autocomplete.infrastructure.rest;

import spark.Filter;

public interface HttpClient<TRoute> {
    String getResource(String path, TRoute handler);

    String postResource(String path, TRoute handler);

    String deleteResource(String path, TRoute handler);
    String options(String path, TRoute handler);
    String before(Filter handler);
}

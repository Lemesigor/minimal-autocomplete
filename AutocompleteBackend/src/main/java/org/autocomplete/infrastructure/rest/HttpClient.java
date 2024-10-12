package org.autocomplete.infrastructure.rest;

public interface HttpClient<TRoute> {
    String getResource(String path, TRoute handler);

    String postResource(String path, TRoute handler);

    String deleteResource(String path, TRoute handler);
}

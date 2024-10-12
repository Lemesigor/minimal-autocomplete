package org.autocomplete.infrastructure.rest;

import spark.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class SparkClient implements  HttpClient<Route> {
    @Override
    public String getResource(String path, Route handler) {
        get(path, handler);
        return null;
    }

    @Override
    public String postResource(String path, Route handler) {
        post(path, handler);
        return  null;
    }
}

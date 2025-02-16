package org.autocomplete.infrastructure.rest;

import spark.*;

import static spark.Spark.*;

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

    @Override
    public String deleteResource(String path, Route handler) {
        delete(path, handler);
        return null;
    }
    @Override
    public String options(String path, Route handler) {
        Spark.options(path, handler);
        return null;
    }

    @Override
    public String before(Filter filter) {
        Spark.before(filter);
        return null;
    }
}

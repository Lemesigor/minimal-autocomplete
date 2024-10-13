package org.autocomplete.infrastructure.controller;

import com.google.gson.Gson;
import org.autocomplete.application.TermService;
import org.autocomplete.domain.Query;
import org.autocomplete.domain.Term;
import org.autocomplete.infrastructure.rest.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class TermsController {
    private static final Logger logger = LoggerFactory.getLogger(TermsController.class);
    private static final String BASE_PATH = "/autocomplete";
    private static final String LIMIT_PARAM = "limit";
    private static final String QUERY_PARAM = "query";

    private final HttpClient<Route> httpClient;
    private final TermService termService;

    public TermsController(HttpClient<Route> httpClient, TermService termService) {
        this.httpClient = httpClient;
        this.termService = termService;
    }


    public void listen() {
        logger.info("Listening on route 4567");
        httpClient.getResource(BASE_PATH, this::getTerms);
        httpClient.postResource(BASE_PATH, this::saveTerm);
        httpClient.deleteResource(BASE_PATH + "/:id", this::deleteTerm);
        httpClient.options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });
        httpClient.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
            response.type("application/json");
        });
    }

    private String getTerms(Request req, Response res) {
        try {
            final var prefix = getQuery(req);
            final var limit = Integer.parseInt(req.queryParams(LIMIT_PARAM));

            final List<Term> terms = termService.findByPrefix(Query.of(prefix, limit));
            TermListResponse response = new TermListResponse(terms);
            return new Gson().toJson(response);
        } catch (Exception e) {
            return handleError(res, e);
        }
    }

    private String saveTerm(Request req, Response res) {
        try {
            var request = new Gson().fromJson(req.body(), CreateTermRequest.class);
            final var savedTerm = termService.save(request.term());
            res.status(201);
            return new Gson().toJson(savedTerm);
        } catch (Exception e) {
            return handleError(res, e);
        }
    }


    private String getQuery(Request req) throws UnsupportedEncodingException {
        var param = req.queryParams(QUERY_PARAM);
        if (param == null) {
            throw new IllegalArgumentException("Query param is required");
        }
        return URLDecoder.decode(param, "UTF-8");
    }

    private String deleteTerm(Request req, Response response) {
        try {
            final var id = req.params(":id");
            termService.delete(Long.parseLong(id));
            response.status(204);
            return "";
        } catch (Exception e) {
            return handleError(response, e);
        }
    }

    private static String handleError(Response res, Exception e) {
        final var error = new ErrorResponse(e.getMessage(), e);
        res.status(error.getStatus());
        return new Gson().toJson(error);
    }


}

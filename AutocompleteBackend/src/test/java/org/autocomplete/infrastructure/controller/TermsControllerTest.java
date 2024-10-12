package org.autocomplete.infrastructure.controller;

import org.autocomplete.application.TermService;
import org.autocomplete.domain.Query;
import org.autocomplete.domain.Term;
import org.autocomplete.infrastructure.rest.HttpClient;
import org.autocomplete.infrastructure.rest.SparkClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TermsControllerTest {


    @Test
    public void returnTerms() throws IOException {
        when(service.findByPrefix(Query.of("test", 10))).thenReturn(List.of(Term.of(1L, "test")));

        List<Map<String, Object>> expectedTerms = List.of(
                Map.of("id", 1.0, "value", "test")
        );

        var response = clientUtils.request("GET", "/autocomplete?query=test&limit=10", null);
        assertThat(response.status, is(200));
        var responseBody = response.json();
        assertThat(responseBody.get("terms"), is(expectedTerms));
    }

    @Test(expected = IOException.class)
    public void thowErrorOnEmptyString () throws IOException {
        clientUtils.request("GET", "/autocomplete?limit=10", null);
    }

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        controller.listen();
        Spark.awaitInitialization();
    }

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }

    private HttpClientUtils clientUtils = new HttpClientUtils();
    private static HttpClient<Route> client = new SparkClient();
    private static TermService service = mock(TermService.class);
    private static TermsController controller = new TermsController(client, service);
}

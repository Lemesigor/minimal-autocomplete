package org.autocomplete;

import org.autocomplete.application.TermService;
import org.autocomplete.infrastructure.InMemoryTermsRepository;
import org.autocomplete.infrastructure.controller.TermsController;
import org.autocomplete.infrastructure.rest.HttpClient;
import org.autocomplete.infrastructure.rest.SparkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;


public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Initialing api");

        HttpClient<Route> client = new SparkClient();
        TermService service = new TermService(new InMemoryTermsRepository());
        TermsController controller = new TermsController(client, service);

        controller.listen();
    }
}
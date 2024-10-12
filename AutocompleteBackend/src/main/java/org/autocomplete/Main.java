package org.autocomplete;

import org.autocomplete.application.TermService;
import org.autocomplete.infrastructure.InMemoryTermsRepository;
import org.autocomplete.infrastructure.controller.TermsController;
import org.autocomplete.infrastructure.jdbc.ConnectionFactory;
import org.autocomplete.infrastructure.jdbc.TermJdbcRepository;
import org.autocomplete.infrastructure.rest.HttpClient;
import org.autocomplete.infrastructure.rest.SparkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Initialing api");

        try {
            Connection connection = ConnectionFactory.createConnection(
                    "localhost",
                    "root",
                    "root",
                    "autocomplete",
                    "3306"
            );

            HttpClient<Route> client = new SparkClient();
            TermJdbcRepository repository = new TermJdbcRepository(connection);
            TermService service = new TermService(repository);
            TermsController controller = new TermsController(client, service);

            controller.listen();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
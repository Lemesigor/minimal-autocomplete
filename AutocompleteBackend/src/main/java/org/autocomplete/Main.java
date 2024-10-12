package org.autocomplete;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.autocomplete.application.TermService;
import org.autocomplete.domain.CacheRepository;
import org.autocomplete.domain.TermsRepository;
import org.autocomplete.infrastructure.controller.TermsController;
import org.autocomplete.infrastructure.repository.CompositeTermsRepository;
import org.autocomplete.infrastructure.repository.ConnectionFactory;
import org.autocomplete.infrastructure.repository.TermInMemoryCacheRepository;
import org.autocomplete.infrastructure.repository.TermJdbcRepository;
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


            TermJdbcRepository repository = new TermJdbcRepository(connection);
            CacheRepository cacheRepository = new TermInMemoryCacheRepository(Caffeine.newBuilder().build());
            TermsRepository compositeRepository = new CompositeTermsRepository(repository, cacheRepository);

            TermService service = new TermService(compositeRepository);
            HttpClient<Route> client = new SparkClient();
            TermsController controller = new TermsController(client, service);

            controller.listen();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
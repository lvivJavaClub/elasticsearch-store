package org.javaclub.elasticstore;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.javaclub.elasticstore.model.Product;
import org.javaclub.elasticstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.Map;

@SpringBootApplication
public class ElasticStoreApplication implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private ProductRepository productRepository;


    public static void main(String[] args) {
        SpringApplication.run(ElasticStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        es.deleteIndex("store");

        printElasticSearchInfo();

        productRepository.save(new Product("Elasticsearch Booking, Spring", 100, "Cool tool book"));
        productRepository.save(new Product("Book Spring", 200, "Description of the weather"));
        productRepository.save(new Product("Engine V8", 302, "Car part"));
        productRepository.save(new Product("Cap V8", 403, "China manufacture"));

        Iterable<Product> books = productRepository.findAll();

        books.forEach(System.out::println);

    }

    //useful for debug, print elastic search details
    private void printElasticSearchInfo() {

        System.out.println("--Configuration of ElasticSearch--");
        Client client = es.getClient();
        Map<String, Settings> asMap = client.settings().getAsGroups();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("--Configuration of ElasticSearch--");
    }

}

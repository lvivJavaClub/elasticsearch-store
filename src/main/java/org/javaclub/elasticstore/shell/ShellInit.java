package org.javaclub.elasticstore.shell;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.javaclub.elasticstore.model.Product;
import org.javaclub.elasticstore.model.ProductAnalyze;
import org.javaclub.elasticstore.repository.ProductAnalyzeRepository;
import org.javaclub.elasticstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@ShellComponent
public class ShellInit {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductAnalyzeRepository productAnalyzeRepository;

    @ShellMethod("init")
    public void init() throws ExecutionException, InterruptedException {

        es.deleteIndex(Product.class);
        es.deleteIndex(ProductAnalyze.class);

        es.createIndex(Product.class);
        es.createIndex(ProductAnalyze.class);

        es.putMapping(Product.class);
        es.putMapping(ProductAnalyze.class);

        printElasticSearchInfo();

        productRepository.save(new Product(1, "Elasticsearch Booking, Spring", 100, "Cool tool for look book"));
        productRepository.save(new Product(2, "Book Spring", 200, "New suggestion for looking do springing"));
        productRepository.save(new Product(3, "Engine V8", 302, "cooling being does new work"));
        productRepository.save(new Product(4, "Cap V8", 403, "Working with better did"));

        productAnalyzeRepository.save(new ProductAnalyze(1, "Analyze Elasticsearch Booking, Spring", 100, "Cool tool for look book"));
        productAnalyzeRepository.save(new ProductAnalyze(2, "Analyze Book Spring", 200, "New suggestion for looking do springing"));
        productAnalyzeRepository.save(new ProductAnalyze(3, "Analyze Engine V8", 302, "cooling being does new work"));
        productAnalyzeRepository.save(new ProductAnalyze(4, "Analyze Cap V8", 403, "Working with better did"));

        Iterable<Product> products = productRepository.findAll();
        Iterable<ProductAnalyze> productAnalyzes = productAnalyzeRepository.findAll();

        System.out.println("--Simple Products--");
        products.forEach(System.out::println);
        System.out.println("--Simple Products--");

        System.out.println("--Products with Analyzer--");
        productAnalyzes.forEach(System.out::println);
        System.out.println("--Products with Analyzer--");
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

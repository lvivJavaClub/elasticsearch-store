package org.javaclub.elasticstore.shell;

import org.javaclub.elasticstore.model.Product;
import org.javaclub.elasticstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.concurrent.ExecutionException;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@ShellComponent
public class SearchShell {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    ProductRepository productRepository;

    @ShellMethod("pSearch")
    public Product addProduct(String title, long price, String description) throws ExecutionException, InterruptedException {
        Product product = new Product(title, price, description);
        return productRepository.save(product);
    }



}

package org.javaclub.elasticstore.shell;

import org.javaclub.elasticstore.model.Product;
import org.javaclub.elasticstore.repository.ProductAnalyzeRepository;
import org.javaclub.elasticstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.concurrent.ExecutionException;

@ShellComponent
public class SearchShell {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductAnalyzeRepository productAnalyzeRepository;

    @ShellMethod("pSearch")
    public Product addProduct(long id, String title, long price, String description) throws ExecutionException, InterruptedException {
        Product product = new Product(id, title, price, description);
        return productRepository.save(product);
    }

}

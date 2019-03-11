package org.javaclub.elasticstore.shell;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.client.Client;
import org.javaclub.elasticstore.model.Product;
import org.javaclub.elasticstore.model.ProductAnalyze;
import org.javaclub.elasticstore.repository.ProductAnalyzeRepository;
import org.javaclub.elasticstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchPhrasePrefixQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@ShellComponent
public class SearchShell {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductAnalyzeRepository productAnalyzeRepository;

    @ShellMethod
    public Product addProduct(long id, String title, long price, String description) throws ExecutionException, InterruptedException {
        Product product = new Product(id, title, price, description);
        return productRepository.save(product);
    }

    @ShellMethod
    public void productS(String content, String field) throws ExecutionException, InterruptedException, IOException {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(
                matchPhraseQuery(field, content)
        );

        Page<ProductAnalyze> searchAnalyzed = productAnalyzeRepository.search(nativeSearchQueryBuilder.build());
        System.out.println("Analyzed product");
        System.out.println(searchAnalyzed.getContent());
        System.out.println("Product");
        Page<Product> search = productRepository.search(nativeSearchQueryBuilder.build());
        System.out.println(search.getContent());
    }


    @ShellMethod
    public Page<Product> productSearch(String content, String fields) throws ExecutionException, InterruptedException, IOException {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(multiMatchQuery(content, fields.split(","))
        );
        NativeSearchQueryBuilder nativeSearchQueryBuilder2 = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder2.withQuery(multiMatchQuery(content, fields.split(","))


        );
        Page<ProductAnalyze> search = productAnalyzeRepository.search(nativeSearchQueryBuilder2.build());
        System.out.println(search.getContent());
        return productRepository.search(nativeSearchQueryBuilder.build());
    }

    @ShellMethod
    public Page<Product> productPhrazeSearch(String content, String field) throws ExecutionException, InterruptedException {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(matchPhrasePrefixQuery(field, content)
                .analyzer("standard")
        );
        return productRepository.search(nativeSearchQueryBuilder.build());
    }

    @ShellMethod
    public Page<Product> productFilter(String filter, String content, String fields) throws ExecutionException, InterruptedException {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(multiMatchQuery(content, fields.split(",")))
                .withFilter(matchQuery("title", filter));
        return productRepository.search(nativeSearchQueryBuilder.build());
    }

    @ShellMethod
    public List<String> indices() throws ExecutionException, InterruptedException {
        Client client = es.getClient();
        AnalyzeRequest request = (new AnalyzeRequest("product-store")
                .text("this is a testing roman2 being did"))
                .analyzer("standard");
        return client.admin().indices().analyze(request).actionGet().getTokens()
                .stream()
                .map(t -> "term: " + t.getTerm())
                .collect(Collectors.toList());
    }

}

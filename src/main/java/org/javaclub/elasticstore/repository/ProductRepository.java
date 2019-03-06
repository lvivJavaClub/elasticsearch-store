package org.javaclub.elasticstore.repository;

import org.javaclub.elasticstore.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}

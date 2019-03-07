package org.javaclub.elasticstore.repository;

import org.javaclub.elasticstore.model.ProductAnalyze;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductAnalyzeRepository extends ElasticsearchRepository<ProductAnalyze, Long> {
}

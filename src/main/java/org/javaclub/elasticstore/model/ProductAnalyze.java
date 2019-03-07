package org.javaclub.elasticstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "product-store-analyze", type = "product_analyze")
@Setting(settingPath = "/cutom_analizer.json")
public class ProductAnalyze {

    @Id
    private long id;
    @Field(type = FieldType.Text, searchAnalyzer = "roman-analyzer")
    private String title;
    private long price;
    @Field(type = FieldType.Text)
    private String description;

    public ProductAnalyze() {
    }

    public ProductAnalyze(long id, String title, long price, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package org.javaclub.elasticstore.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "store", type = "product_type")
public class Product {

    private String id;
    private String title;
    private long price;
    private String description;

    public Product() {
    }

    public Product(String title, long price, String description) {
        this.id = UUID.fromString(title).toString();
        this.title = title;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

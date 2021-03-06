package org.javaclub.elasticstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Document(indexName = "product-store", type = "product_type")
public class Product {

    @Id
    private long id;
    @Field(type = Text)
    private String title;
    private long price;
    @Field(type = Text)
    private String description;

    public Product() {
    }

    public Product(long id, String title, long price, String description) {
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

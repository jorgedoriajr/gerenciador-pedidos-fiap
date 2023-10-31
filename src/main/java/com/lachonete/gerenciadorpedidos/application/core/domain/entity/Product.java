package com.lachonete.gerenciadorpedidos.application.core.domain.entity;

import com.lachonete.gerenciadorpedidos.application.core.domain.valueobject.Image;
import com.lachonete.gerenciadorpedidos.application.core.domain.valueobject.Money;
import com.lachonete.gerenciadorpedidos.application.core.domain.valueobject.ProductCategory;
import com.lachonete.gerenciadorpedidos.application.core.domain.valueobject.ProductId;

import java.util.List;
import java.util.UUID;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private String description;
    private Money price;
    private ProductCategory category;
    private List<Image> images;

    public Product(ProductId productId) {
        this.setId(productId);
    }

    public Product() {
        super();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public List<Image> getImages() {
        return images;
    }

    public Money getPrice() {
        return price;
    }


    public static final class ProductBuilder {
        private ProductId id;
        private String name;
        private String description;
        private Money price;
        private ProductCategory category;
        private List<Image> images;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withId(ProductId id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder withPrice(Money price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withCategory(ProductCategory category) {
            this.category = category;
            return this;
        }

        public ProductBuilder withImages(List<Image> images) {
            this.images = images;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.category = this.category;
            product.description = this.description;
            product.price = this.price;
            product.images = this.images;
            product.name = this.name;
            return product;
        }
    }
}

package com.lachonete.gerenciadorpedidos.configuration;
import com.lachonete.gerenciadorpedidos.adapters.JpaDatabase;
import com.lachonete.gerenciadorpedidos.adapters.repositories.ProductRepository;
import com.lachonete.gerenciadorpedidos.ports.database.Database;
import com.lachonete.gerenciadorpedidos.ports.presenters.ProductCreatedOutputBoundary;
import com.lachonete.gerenciadorpedidos.ports.presenters.ProductsOutputBoundary;
import com.lachonete.gerenciadorpedidos.ports.usescases.product.add.AddProductInputBoundary;
import com.lachonete.gerenciadorpedidos.presenters.ProductCreatedPresenter;
import com.lachonete.gerenciadorpedidos.presenters.ProductsPresenter;
import com.lachonete.gerenciadorpedidos.usecases.product.add.AddProduct;
import com.lachonete.gerenciadorpedidos.usecases.product.get.GetProducts;
import com.lachonete.gerenciadorpedidos.usecases.product.get.GetProductsInputBoundary;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@Configuration
@EntityScan("com.lachonete.gerenciadorpedidos.adapters.data")
@EnableJpaRepositories("com.lachonete.gerenciadorpedidos.adapters.repositories")
public class ApplicationConfiguration {
    @Bean
    public Database database(ProductRepository productRepository) {
        return new JpaDatabase(productRepository);
    }

    @Bean
    public AddProductInputBoundary addProductInputBoundary(ProductCreatedOutputBoundary productCreatedOutputBoundary, Database database) {
        return new AddProduct(productCreatedOutputBoundary, database.productGateway());
    }

    @Bean
    public GetProductsInputBoundary getProductInputBoundary(ProductsOutputBoundary productsOutputBoundary, Database database) {
        return new GetProducts(productsOutputBoundary, database.productGateway());
    }

    @Bean
    public ProductCreatedOutputBoundary productCreatedOutputBoundary() {
        return new ProductCreatedPresenter();
    }

    @Bean
    public ProductsOutputBoundary productsOutputBoundary() {return new ProductsPresenter(); }

}

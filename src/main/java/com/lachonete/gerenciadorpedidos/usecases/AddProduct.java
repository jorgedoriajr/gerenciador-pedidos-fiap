package com.lachonete.gerenciadorpedidos.usecases;


import com.lachonete.gerenciadorpedidos.entities.Product;
import com.lachonete.gerenciadorpedidos.entities.valueobject.Image;
import com.lachonete.gerenciadorpedidos.entities.valueobject.Money;
import com.lachonete.gerenciadorpedidos.ports.database.ProductGateway;
import com.lachonete.gerenciadorpedidos.ports.presenters.ProcuctCreatedOutputBoundary;
import com.lachonete.gerenciadorpedidos.ports.usescases.AddProduct.AddProductInputBoundary;
import com.lachonete.gerenciadorpedidos.ports.usescases.AddProduct.AddProductRequest;
import com.lachonete.gerenciadorpedidos.ports.usescases.AddProduct.NewProductResponse;

import java.util.UUID;

public class AddProduct implements AddProductInputBoundary {
    private final ProcuctCreatedOutputBoundary presenter;
    private final ProductGateway productGateway;

    public AddProduct(ProcuctCreatedOutputBoundary presenter, ProductGateway productGateway) {
        this.presenter = presenter;
        this.productGateway = productGateway;
    }

    public void execute(AddProductRequest request) {
        validateProduct(request);
        UUID id = addProduct(request);

        NewProductResponse responseModel = new NewProductResponse(id);
        presenter.present(responseModel);
    }

    private void validateProduct(final AddProductRequest request) {
        if (productAlreadyExists(request))
            throw new ProductGateway.ProductAlreadyByNameExistsException();
    }

    private boolean productAlreadyExists(final AddProductRequest request) {
        return true;
    }

    private UUID addProduct(AddProductRequest request) {
        return productGateway.add(
                Product.ProductBuilder.aProduct()
                        .withName(request.getName())
                        .withCategory(request.getCategory())
                        .withPrice(new Money(request.getPrice()))
                        .withDescription(request.getDescription())
                        .withImages(request.getImages().stream().map(Image::new).toList())
                        .build()).getValue();
    }
}

package com.lachonete.gerenciadorpedidos.application.core.usecase.order;

import com.lachonete.gerenciadorpedidos.application.core.domain.entity.Order;
import com.lachonete.gerenciadorpedidos.application.core.domain.entity.OrderItem;
import com.lachonete.gerenciadorpedidos.application.core.domain.entity.Product;
import com.lachonete.gerenciadorpedidos.application.core.domain.valueobject.OrderId;
import com.lachonete.gerenciadorpedidos.application.ports.in.order.OrderCheckoutInputPort;
import com.lachonete.gerenciadorpedidos.application.ports.out.order.OrderCheckoutOutputPort;
import com.lachonete.gerenciadorpedidos.application.ports.out.product.FindProductOutputPort;

import java.util.Optional;


public class OrderCheckoutUseCase implements OrderCheckoutInputPort {
    private final OrderCheckoutOutputPort orderCheckoutOutputPort;
    private final FindProductOutputPort findProductOutputPort;

    public OrderCheckoutUseCase(OrderCheckoutOutputPort checkoutOutputPort, FindProductOutputPort findProductOutputPort) {
        this.orderCheckoutOutputPort = checkoutOutputPort;
        this.findProductOutputPort = findProductOutputPort;
    }

    @Override
    public OrderId checkout(Order order) {
        loadOrderPriceInfo(order);
        var orderSaved = orderCheckoutOutputPort.persist(order);
        order.initializeOrder();
        //order.getItems().forEach(this::loadPriceProductsInfo);
        return orderSaved.getId();
    }

    void loadOrderPriceInfo (Order order){
        order.setPriceInfo(order);
    }

    @Override
    public void loadPriceProductsInfo(OrderItem orderitem) {
        Optional<Product> productEntity = findProductOutputPort.find(orderitem.getProduct());
        if (productEntity.isPresent()){
            Product p = productEntity.get();
            orderitem.setSubTotal(p, orderitem.getQuantity());
        }
    }


}

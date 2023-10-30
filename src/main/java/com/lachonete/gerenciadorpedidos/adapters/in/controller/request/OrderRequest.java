package com.lachonete.gerenciadorpedidos.adapters.in.controller.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private List<OrderItemRequest> items;
}

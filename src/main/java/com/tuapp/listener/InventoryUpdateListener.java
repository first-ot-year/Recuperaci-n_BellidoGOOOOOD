package com.tuapp.listener;

import com.tuapp.event.OrderCreatedEvent;
import com.tuapp.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryUpdateListener {

    private static final Logger logger = LogManager.getLogger(InventoryUpdateListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        for (Product product : event.getProducts()) {
            logger.info("📦 Reduciendo stock del producto: {} (cantidad: {})", product.getName(), product.getQuantity());
        }
    }
}
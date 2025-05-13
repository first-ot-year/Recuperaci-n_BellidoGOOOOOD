package com.tuapp.listener;

import com.tuapp.event.OrderCreatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogListener {

    private static final Logger logger = LogManager.getLogger(AuditLogListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        logger.info("🧾 Pedido registrado: ID={}, Email={}, Productos={}",
                event.getOrderId(), event.getEmail(), event.getProducts().size());
    }
}
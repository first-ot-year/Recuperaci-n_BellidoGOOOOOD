package com.tuapp.service;

import com.tuapp.event.OrderCreatedEvent;
import com.tuapp.model.OrderRequest;
import com.tuapp.model.Product;
import com.tuapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public String processOrder(OrderRequest request) {
        List<Product> requestedProducts = request.getProducts();

        for (Product requested : requestedProducts) {
            Product productInDb = productRepository.findByName(requested.getName())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + requested.getName()));

            if (productInDb.getStock() < requested.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + requested.getName());
            }

            // Reducir el stock
            productInDb.setStock(productInDb.getStock() - requested.getQuantity());
            productRepository.save(productInDb);
        }

        Long orderId = System.currentTimeMillis();

        publisher.publishEvent(new OrderCreatedEvent(this, orderId, request.getEmail(), requestedProducts));
        return "Orden recibida. ID: " + orderId;
    }
}

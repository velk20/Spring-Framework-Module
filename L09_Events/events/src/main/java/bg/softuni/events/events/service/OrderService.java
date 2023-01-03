package bg.softuni.events.events.service;

import bg.softuni.events.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void createOrder(String productId, Integer quantity) {
        LOGGER.info("Creating order fro product {} with quantity{}.", productId, quantity);
        // do some work

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                OrderService.class.getSimpleName(),
                productId
        );

        eventPublisher.publishEvent(orderCreatedEvent);

    }
}

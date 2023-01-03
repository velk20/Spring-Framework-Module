package bg.softuni.events.events.service;

import bg.softuni.events.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreated(OrderCreatedEvent event) {
        LOGGER.info("Sending email to user order {}", event.getOrderId());

    }
}

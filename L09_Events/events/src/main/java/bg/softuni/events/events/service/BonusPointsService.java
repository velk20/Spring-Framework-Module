package bg.softuni.events.events.service;

import bg.softuni.events.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BonusPointsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonusPointsService.class);

    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreated(OrderCreatedEvent event) {
        LOGGER.info("Adding bonus points to user order {}", event.getOrderId());
    }
}

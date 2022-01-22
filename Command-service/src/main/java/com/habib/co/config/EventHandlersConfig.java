package com.habib.co.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.habib.co.events.inventory.InventoryEvent;

import java.util.function.Consumer;

@Configuration
public class EventHandlersConfig {

    @Autowired
    private OrderStatusUpdateEventHandler orderEventHandler;

//    @Bean
//    public Consumer<PaymentEvent> paymentEventConsumer(){
//        return pe -> {
//            orderEventHandler.updateOrder(pe.getPayment().getOrderId(), po -> {
//                po.setPaymentStatus(pe.getPaymentStatus());
//            });
//        };
//    }

    @Bean
    public Consumer<InventoryEvent> inventoryEventConsumer(){
        return ie -> {
            orderEventHandler.updateOrder(ie.getInventory().getOrderId(), po -> {
                po.setInventoryStatus(ie.getStatus());
                System.out.println("********"+ie.getStatus());
            });
        };
    }

}

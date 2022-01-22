package com.habib.co.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habib.co.entities.Commande;
import com.habib.co.events.command.OrderStatus;
import com.habib.co.events.inventory.InventoryStatus;
import com.habib.co.repo.ICommande;
import com.habib.co.repo.IComposant;
import com.habib.co.services.OrderStatusPublisher;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class OrderStatusUpdateEventHandler {

	@Autowired
	ICommande crepo;
	@Autowired
	IComposant cprepo;
	@Autowired
	private OrderStatusPublisher publisher;

    @Transactional
    public void updateOrder(final long id, Consumer<Commande> consumer){
        this.crepo
                .findById(id)
                .ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(Commande purchaseOrder){
        if(Objects.isNull(purchaseOrder.getInventoryStatus()) )
            return;
        boolean isComplete = InventoryStatus.RESERVED.equals(purchaseOrder.getInventoryStatus());
        var orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isComplete){
            this.publisher.raiseOrderEvent(purchaseOrder, orderStatus);
        }
    }

}

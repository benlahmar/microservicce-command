package com.habib.co.events.command;

import java.util.Date;
import java.util.UUID;

import com.habib.co.dto.CommandDto;
import com.habib.co.events.Event;

public class OrderEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private CommandDto commande;
    private OrderStatus orderStatus;

    public OrderEvent() {
    }

    public OrderEvent(CommandDto purchaseOrder, OrderStatus orderStatus) {
        this.commande = purchaseOrder;
        this.orderStatus = orderStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    
    public CommandDto getCommande() {
		return commande;
	}

	public void setCommande(CommandDto commande) {
		this.commande = commande;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}

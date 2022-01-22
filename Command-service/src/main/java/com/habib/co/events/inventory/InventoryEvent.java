package com.habib.co.events.inventory;

import java.util.Date;
import java.util.UUID;

import com.habib.co.dto.InventoryDto;
import com.habib.co.events.Event;

public class InventoryEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private InventoryDto inventory;
    private InventoryStatus status;

    public InventoryEvent() {
    }

    public InventoryEvent(InventoryDto inventory, InventoryStatus status) {
        this.inventory = inventory;
        this.status = status;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public InventoryStatus getStatus() {
        return status;
    }

}

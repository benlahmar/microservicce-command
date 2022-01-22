package com.habib.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habib.co.dto.CommandDto;
import com.habib.co.dto.ComposantDto;
import com.habib.co.entities.Commande;
import com.habib.co.entities.Composant;
import com.habib.co.events.command.OrderEvent;
import com.habib.co.events.command.OrderStatus;

import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSink;

    public void raiseOrderEvent(final Commande cmd, OrderStatus orderStatus){
        var dto = new CommandDto(
                cmd.getOrderId(),
                cmd.getUserId()
        );
        
        //dto.getComdto().addAll(cmd.getComposants())
        List<Composant> comp = cmd.getComposants();
        for (Composant cp : comp) {
			ComposantDto cpdto=new ComposantDto();
			cpdto.setCompId(cp.getIdcomp());
			cpdto.setPrix(cp.getPrix());
			cpdto.setProductId(cp.getIdproduit());
			cpdto.setQuantite(cp.getQuantite());
			cpdto.setOrderId(cmd.getOrderId());
			dto.getComdto().add(cpdto);
		}
        var orderEvent = new OrderEvent(dto, orderStatus);
        this.orderSink.tryEmitNext(orderEvent);
    }

}
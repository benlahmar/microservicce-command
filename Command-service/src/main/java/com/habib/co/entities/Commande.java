package com.habib.co.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habib.co.events.command.OrderStatus;
import com.habib.co.events.inventory.InventoryStatus;
import com.habib.co.events.payment.PaymentStatus;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long orderId;
	@CreatedDate
	LocalDate date;
	
	long userId;
	
	@Transient
	User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "command",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Composant> composants=new ArrayList<>();
	
	OrderStatus orderStatus;
    PaymentStatus paymentStatus;
    InventoryStatus inventoryStatus;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Composant> getComposants() {
		return composants;
	}
	public void setComposants(List<Composant> composants) {
		this.composants = composants;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public InventoryStatus getInventoryStatus() {
		return inventoryStatus;
	}
	public void setInventoryStatus(InventoryStatus inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

}

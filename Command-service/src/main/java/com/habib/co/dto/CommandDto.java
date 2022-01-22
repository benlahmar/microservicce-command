package com.habib.co.dto;

import java.util.ArrayList;
import java.util.List;

public class CommandDto {

	
	private long orderId;
    private long userId;
	List<ComposantDto> comdto=new ArrayList<>();
	
    public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
	public List<ComposantDto> getComdto() {
		return comdto;
	}
	public void setComdto(List<ComposantDto> comdto) {
		this.comdto = comdto;
	}
	public CommandDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommandDto(long orderId, long userId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
	}
    
    
    
    
}

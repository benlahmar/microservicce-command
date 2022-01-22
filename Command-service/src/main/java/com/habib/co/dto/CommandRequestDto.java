package com.habib.co.dto;

import java.util.ArrayList;
import java.util.List;

public class CommandRequestDto {

	private long userId;
    private long orderId;
	List<ComposantDto> comdto=new ArrayList<>();

    
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public List<ComposantDto> getComdto() {
		return comdto;
	}
	public void setComdto(List<ComposantDto> comdto) {
		this.comdto = comdto;
	}
	
}

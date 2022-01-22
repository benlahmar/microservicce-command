package com.habib.co.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.habib.co.entities.User;

@FeignClient(url = "http://localhost:8044",name="customer-service")
public interface ICustomer {

	@GetMapping(path = "/users/{idu}")
	public User findbyid(@PathVariable(name = "idu") long iduser);
}

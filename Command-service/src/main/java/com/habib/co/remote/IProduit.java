package com.habib.co.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.habib.co.entities.Produit;

@FeignClient(name = "catalogue-service", url = "http://localhost:8045")
public interface IProduit {

	@GetMapping(path = "/produits/{id}")
	public Produit getprd(@PathVariable(name = "id") long idp);
}

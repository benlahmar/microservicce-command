package com.habib.co.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Composant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long idcomp;
	double prix;
	int quantite;
	
	@JsonBackReference
	@ManyToOne
	Commande command;
	
	long idproduit;
	
	@Transient
	Produit produit;
	
	
	public long getIdcomp() {
		return idcomp;
	}
	public void setIdcomp(long idcomp) {
		this.idcomp = idcomp;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Commande getCommand() {
		return command;
	}
	public void setCommand(Commande command) {
		this.command = command;
	}
	public long getIdproduit() {
		return idproduit;
	}
	public void setIdproduit(long idproduit) {
		this.idproduit = idproduit;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
}

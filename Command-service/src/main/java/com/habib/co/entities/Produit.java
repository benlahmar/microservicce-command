package com.habib.co.entities;



public class Produit {
	
		long id;
		String desg,description;
		double prix;
		int quantite;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getDesg() {
			return desg;
		}
		public void setDesg(String desg) {
			this.desg = desg;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
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
		
		
}

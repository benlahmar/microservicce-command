package com.habib.co.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.habib.co.entities.Commande;

public interface ICommande extends JpaRepository<Commande, Long> {

}

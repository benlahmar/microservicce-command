package com.habib.co.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habib.co.dto.CommandRequestDto;
import com.habib.co.dto.ComposantDto;
import com.habib.co.entities.Commande;
import com.habib.co.entities.Composant;
import com.habib.co.events.command.OrderStatus;
import com.habib.co.repo.ICommande;
import com.habib.co.repo.IComposant;

@Service
public class CommandeService {

	@Autowired
	ICommande crepo;
	@Autowired
	IComposant cprepo;
	@Autowired
	private OrderStatusPublisher publisher;
	
	public Commande addcmd(Commande cmd, long userId)
	{
		cmd.setUserId(userId);
		return crepo.save(cmd);
	}
	
	public List<Commande> allcmd(long userId)
	{
		return crepo.findAll();
	}
	
	public Commande findcmd(long id)
	{
		return crepo.findById(id).get();
	}
	
	public Composant addcomposant(Composant cp,long idcmd)
	{
		Commande c = crepo.findById(idcmd).get();
		c.getComposants().add(cp);
		cp.setCommand(c);
		return cprepo.save(cp);
	}
	public List<Composant> allcp(long idcmd)
	{
		return cprepo.findAll();
	}
	
	public Commande createcommand(CommandRequestDto orderRequestDTO)
	{
		Commande c=this.dtoToEntity(orderRequestDTO);
		long idu=orderRequestDTO.getUserId();
		c.setUserId(idu);
		//cprepo.save(cp);
		crepo.save(c);
		//apres publier l'evenement
		 this.publisher.raiseOrderEvent(c, OrderStatus.ORDER_CREATED);
	        return c;
		
	}
	
	
	public Commande dtoToEntity(CommandRequestDto cdto)
	{
		Commande c=new Commande();
		c.setOrderId(cdto.getOrderId());
		c.setUserId(cdto.getUserId());
		c.setDate(LocalDate.now());
		c.setUserId(cdto.getUserId());
		List<Composant> composants=new ArrayList<>();
		List<ComposantDto> cmpdto = cdto.getComdto();
		for (ComposantDto cp : cmpdto) {
			Composant comp=new Composant();
			comp.setIdcomp(cp.getCompId());
			comp.setIdproduit(cp.getProductId());
			comp.setPrix(cp.getPrix());
			comp.setQuantite(cp.getQuantite());
			
			composants.add(comp);
			c.getComposants().add(comp);
			comp.setCommand(c);
		}
		
		//c.setComposants(composants);
		c.setOrderStatus(OrderStatus.ORDER_CREATED);
		//etc etc....
		return c;
	}
}

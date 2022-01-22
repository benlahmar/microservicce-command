package com.habib.co.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.habib.co.dto.CommandRequestDto;
import com.habib.co.entities.Commande;
import com.habib.co.entities.Composant;
import com.habib.co.entities.User;
import com.habib.co.remote.ICustomer;
import com.habib.co.remote.IProduit;
import com.habib.co.services.CommandeService;

@RestController
public class CmdRest {

	@Autowired
	CommandeService cmdservice;
	@Autowired
	ICustomer remoteuser;
	@Autowired
	IProduit remoteprd;
	
	@PostMapping(path = "/commandes/{iduser}")
	public Commande save(@RequestBody Commande cmd,@PathVariable(name = "iduser") long userId)
	{
		return cmdservice.addcmd(cmd, userId);
	}
	
	@PostMapping("/commande/add/{iduser}")
	public Commande add(@RequestBody CommandRequestDto orderRequestDTO,@PathVariable(name = "iduser") long userId)
	{
		orderRequestDTO.setUserId(userId);
		return cmdservice.createcommand(orderRequestDTO);
	}
	
	@GetMapping(path = "/commandes/user/{iduser}")
	public List<Commande> all(@PathVariable(name = "iduser") long userId)
	{
		List<Commande> cs = cmdservice.allcmd(userId);
		for (Commande c : cs) {
			List<Composant> cps = c.getComposants();
			for (Composant cp : cps) {
				cp.setProduit(remoteprd.getprd(cp.getIdproduit()));
			}
			
		}
		return cs;
	}
	
	@GetMapping(path = "/commandes/{idcmd}")
	public Commande fincmdbyid(@PathVariable(name = "idcmd") long id)
	{
		Commande c = cmdservice.findcmd(id);
		long idu = c.getUserId();
		User u = remoteuser.findbyid(idu);
		c.setUser(u);
		c.getComposants()
		.forEach(x-> 
		x.setProduit(
				remoteprd.getprd(x.getIdproduit())
				)
		);
		
		return c;
	}
	
	@PostMapping("/commandes/{idcmd}/composants")
	public Composant addcmp(@RequestBody Composant cp,@PathVariable long idcmd)
	{
		return cmdservice.addcomposant(cp, idcmd);
	}
	
	@GetMapping("/commandes/{idcmd}/composants")
	public List<Composant> allcp(@PathVariable long idcmd)
	{
		return cmdservice.allcp(idcmd);
	}
}

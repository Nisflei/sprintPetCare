package com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petcare.model.PetCare;
import com.petcare.repository.PetCareRepository;

@Controller
@RequestMapping("/")
public class PetCareController {
	
	@Autowired
	private PetCareRepository petcareRepository;
	
	
	@GetMapping("hello")
	public String hello(Model request) {
		request.addAttribute("nome", "PESSOAL..!");
		return "hello";
	}
	
	@GetMapping("/listaDados")
	public String listaDados(Model request) {
		List<PetCare> lista = petcareRepository.findAll();
		request.addAttribute("listaDados", lista);	
		return "listaDados";
	}
	
	@GetMapping("/formulario")
	public String formulario() {
		return "formulario";
	}
	
	@PostMapping("/formularioNovo")
	public String formularioNovo(PetCare requisicao) {
		requisicao.setStatus("Pendente");
		petcareRepository.save(requisicao);
		return "redirect:/listaDados";
	}
	
	@GetMapping("/update/{id}/{status}")
	public String update(@PathVariable Long id, @PathVariable String status) {
		
		PetCare pet = petcareRepository.getById(id);
		pet.setStatus(status);
		
		petcareRepository.save(pet);
		
		//return "redirect:/listaDados";  ## alterado para abrir o novo template
		return "redirect:/index";
	}
	
	// =========== usando Template Freee
	
	@GetMapping("/index")
	public String index(Model request) {
		List<PetCare> lista = petcareRepository.findAll();
		request.addAttribute("listaDados", lista);	
		return "/anipat-master/index";
	}
	
	@GetMapping("/denuncia")
	public String denuncia() {
		return "/anipat-master/denuncia";
	}
	
	@PostMapping("/denunciaNovo")
	public String denunciaNovo(PetCare requisicao) {
		requisicao.setStatus("Pendente");
		petcareRepository.save(requisicao);
		return "redirect:/index";
	}

}

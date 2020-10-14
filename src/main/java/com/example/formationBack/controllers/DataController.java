package com.example.formationBack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.formationBack.models.DofusMonster;
import com.example.formationBack.models.Pokemon;
import com.example.formationBack.repositories.DofusRepository;
import com.example.formationBack.repositories.PokemonRepository;

@RestController
@RequestMapping("/api")
public class DataController {
	
	@Autowired
	DofusRepository dofusRepository;
	
	@Autowired
	PokemonRepository pokemonRepository;
	
	@GetMapping("/dofus")
	public  List<DofusMonster> findAllDofus() throws Exception {
		try {
			return dofusRepository.findAll();
		} catch (BadCredentialsException e) {
			throw new Exception("Error when fetching Dofus monsters", e);
		}
		
	}
	
	@GetMapping("/pokemon")
	public  List<Pokemon> findAllPokemon() throws Exception {
		try {
			return pokemonRepository.findAll();
		} catch (BadCredentialsException e) {
			throw new Exception("Error when fetching pokemons", e);
		}
		
	}

}

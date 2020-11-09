package com.example.formationBack.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.formationBack.models.DofusMonster;
import com.example.formationBack.models.Pokemon;
import com.example.formationBack.repositories.DofusRepository;
import com.example.formationBack.repositories.PokemonRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping("/dofus/{id}")
	public Optional<DofusMonster> getDofusById(@PathVariable("id") int id) throws Exception {
		
			try {
				return dofusRepository.findById(id);
				
			} catch (BadCredentialsException e) {
				throw new Exception("This dofus monster does not exist", e);
			}
	}
	
	@PostMapping("/dofus")
	public String addDofus(@RequestBody DofusMonster dofus) throws Exception {
		try {
			DofusMonster monster = new DofusMonster();
			monster.setName(dofus.getName());
			monster.setType(dofus.getType());
			monster.setImgUrl(dofus.getImgUrl());
			monster.setUrl(dofus.getUrl());
			dofusRepository.save(monster);
			return "Dofus monster successfully created!";
		} catch (BadCredentialsException e) {
			throw new Exception("Error when creating new dofus monster");
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
	
	@GetMapping("/pokemon/{id}")
	public Optional<Pokemon> getPokemonById(@PathVariable("id") String id) throws Exception {
		
			try {
				return pokemonRepository.findById(id);
				
			} catch (BadCredentialsException e) {
				throw new Exception("This pokemon does not exist", e);
			}
	}
	
	@PostMapping("/pokemon")
	public String addPokemon(@RequestBody Pokemon pokemon) throws Exception {
		try {
			Pokemon monster = new Pokemon();
			monster.setId(UUID.randomUUID().toString());
			monster.setName(pokemon.getName());
			monster.setImageUrl(pokemon.getImageUrl());
			pokemonRepository.save(monster);
			return "Pokemon is successfully created!";
		} catch (BadCredentialsException e) {
			throw new Exception("Error when creating new pokemon");
		}
		
	}

}

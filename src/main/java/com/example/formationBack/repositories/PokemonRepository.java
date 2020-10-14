package com.example.formationBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.formationBack.models.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, String> {

}

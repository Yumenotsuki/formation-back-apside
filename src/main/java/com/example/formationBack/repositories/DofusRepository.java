package com.example.formationBack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.formationBack.models.DofusMonster;

public interface DofusRepository extends JpaRepository<DofusMonster, Integer> {

}

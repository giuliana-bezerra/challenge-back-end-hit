package com.amedigital.startwarsgame.planets.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, Long> {
  List<Planet> findAll();
}

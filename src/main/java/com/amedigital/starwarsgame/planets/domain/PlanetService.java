package com.amedigital.starwarsgame.planets.domain;

import java.util.List;
import java.util.Optional;

import com.amedigital.starwarsgame.common.repository.QueryBuilder;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
  private PlanetRepository planetRepository;

  public PlanetService(PlanetRepository planetRepository) {
    this.planetRepository = planetRepository;
  }

  public Planet create(Planet planet) {
    return planetRepository.save(planet);
  }

  public List<Planet> list(String terrain, String climate, Integer filmsCount) {
    Example<Planet> query = QueryBuilder.makeQuery(new Planet(climate, terrain, filmsCount));
    return planetRepository.findAll(query);
  }

  public Optional<Planet> get(Long id) {
    return planetRepository.findById(id);
  }

  public Optional<Planet> getByName(String name) {
    return planetRepository.findByName(name);
  }

  public void remove(Long id) {
    planetRepository.deleteById(id);
  }

}

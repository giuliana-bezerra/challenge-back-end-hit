package com.amedigital.startwarsgame.planets.domain;

import java.util.List;

import com.amedigital.common.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
  @Autowired
  private PlanetRepository planetRepository;

  public Planet create(Planet planet) {
    return planetRepository.save(planet);
  }

  public List<Planet> list(Long id, String name) {
    Example<Planet> query = QueryBuilder.makeQuery(new Planet(id, name));
    return planetRepository.findAll(query);
  }
}

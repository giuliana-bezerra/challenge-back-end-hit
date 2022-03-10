package com.amedigital.startwarsgame.planets.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
  @Autowired
  private PlanetRepository planetRepository;

  public Planet create(Planet planet) {
    return planetRepository.save(planet);
  }

  public List<Planet> list() {
    return planetRepository.findAll();
  }
}

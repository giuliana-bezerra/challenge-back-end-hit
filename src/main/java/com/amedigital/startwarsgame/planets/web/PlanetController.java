package com.amedigital.startwarsgame.planets.web;

import java.util.List;

import com.amedigital.startwarsgame.planets.domain.Planet;
import com.amedigital.startwarsgame.planets.domain.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planets")
public class PlanetController {
  @Autowired
  private PlanetService planetService;

  @PostMapping
  public ResponseEntity<Planet> create(@RequestBody Planet planet) {
    Planet createdPlanet = planetService.create(planet);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanet);
  }

  @GetMapping
  public ResponseEntity<List<Planet>> list() {
    List<Planet> planets = planetService.list();
    return ResponseEntity.ok(planets);
  }
}

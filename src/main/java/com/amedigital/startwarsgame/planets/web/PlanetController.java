package com.amedigital.startwarsgame.planets.web;

import java.util.List;

import com.amedigital.startwarsgame.planets.domain.Planet;
import com.amedigital.startwarsgame.planets.domain.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<List<Planet>> list(@RequestParam(required = false) Long id,
      @RequestParam(required = false) String name) {
    List<Planet> planets = planetService.list(id, name);
    return ResponseEntity.ok(planets);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Planet> get(@PathVariable("id") Long id) {
    return planetService.get(id).map(planet -> ResponseEntity.ok(planet))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
    planetService.remove(id);
    return ResponseEntity.ok().build();
  }
}

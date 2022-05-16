package com.amedigital.startwarsgame.planets.web;

import static com.amedigital.startwarsgame.planets.web.PlanetMapper.toListResponse;
import static com.amedigital.startwarsgame.planets.web.PlanetMapper.toPlanet;
import static com.amedigital.startwarsgame.planets.web.PlanetMapper.toResponse;

import java.util.List;

import javax.validation.Valid;

import com.amedigital.startwarsgame.planets.api.PlanetRequest;
import com.amedigital.startwarsgame.planets.api.PlanetResponse;
import com.amedigital.startwarsgame.planets.domain.Planet;
import com.amedigital.startwarsgame.planets.domain.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/planets")
public class PlanetController {
  @Autowired
  private PlanetService planetService;

  @PostMapping
  public ResponseEntity<PlanetResponse> create(@RequestBody @Valid PlanetRequest planet) {
    Planet createdPlanet = planetService.create(toPlanet(planet));
    return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(createdPlanet));
  }

  @GetMapping
  public ResponseEntity<List<PlanetResponse>> list(@RequestParam(required = false) String terrain,
      @RequestParam(required = false) String climate, @RequestParam(required = false) Integer filmsCount) {
    List<Planet> planets = planetService.list(terrain, climate, filmsCount);
    return ResponseEntity.ok(toListResponse(planets));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlanetResponse> get(@PathVariable("id") Long id) {
    return planetService.get(id).map(planet -> ResponseEntity.ok(toResponse(planet)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<PlanetResponse> getByName(@PathVariable("name") String name) {
    return planetService.getByName(name).map(planet -> ResponseEntity.ok(toResponse(planet)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
    try {
      planetService.remove(id);
      return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "planet not found", ex);
    }
  }
}

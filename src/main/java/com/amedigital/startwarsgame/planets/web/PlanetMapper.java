package com.amedigital.startwarsgame.planets.web;

import java.util.List;
import java.util.stream.Collectors;

import com.amedigital.startwarsgame.planets.api.PlanetRequest;
import com.amedigital.startwarsgame.planets.api.PlanetResponse;
import com.amedigital.startwarsgame.planets.domain.Planet;

public class PlanetMapper {
  public static Planet toPlanet(PlanetRequest planetRequest) {
    return new Planet(planetRequest.getName(), planetRequest.getClimate(), planetRequest.getTerrain(),
        planetRequest.getFilmsCount());
  }

  public static PlanetResponse toResponse(Planet planet) {
    return new PlanetResponse(planet.getId(), planet.getName(), planet.getClimate(), planet.getTerrain(),
        planet.getFilmsCount());
  }

  public static List<PlanetResponse> toListResponse(List<Planet> planets) {
    return planets.stream().map(planet -> toResponse(planet)).collect(Collectors.toList());
  }
}

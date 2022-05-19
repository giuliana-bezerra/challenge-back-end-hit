package com.amedigital.starwarsgame.planets.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlanetRequest {
  @NotEmpty(message = "name is required")
  private String name;

  @NotEmpty(message = "climate is required")
  private String climate;

  @NotEmpty(message = "terrain is required")
  private String terrain;

  @NotNull(message = "filmsCount is required")
  private Integer filmsCount;

  public PlanetRequest() {

  }

  public PlanetRequest(String name,
      String climate,
      String terrain,
      Integer filmsCount) {
    this.name = name;
    this.climate = climate;
    this.terrain = terrain;
    this.filmsCount = filmsCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClimate() {
    return climate;
  }

  public void setClimate(String climate) {
    this.climate = climate;
  }

  public String getTerrain() {
    return terrain;
  }

  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }

  public Integer getFilmsCount() {
    return filmsCount;
  }

  public void setFilmsCount(Integer filmsCount) {
    this.filmsCount = filmsCount;
  }

}

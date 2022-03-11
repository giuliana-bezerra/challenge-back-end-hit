package com.amedigital.startwarsgame.planets.api;

public class PlanetResponse {
  private Long id;
  private String name;
  private String climate;
  private String terrain;
  private Integer filmsCount;

  public PlanetResponse() {

  }

  public PlanetResponse(Long id, String name, String climate, String terrain, Integer filmsCount) {
    this.id = id;
    this.name = name;
    this.climate = climate;
    this.terrain = terrain;
    this.filmsCount = filmsCount;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

package com.amedigital.startwarsgame.planets.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "planets")
public class Planet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String Climate;
  private String terrain;
  private Integer filmsCount;

  public Planet() {
  }

  public Planet(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Planet(String name, String climate, String terrain, Integer filmsCount) {
    this.name = name;
    Climate = climate;
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
    return Climate;
  }

  public void setClimate(String climate) {
    Climate = climate;
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

  @Override
  public String toString() {
    return "Planet [Climate=" + Climate + ", filmsCount=" + filmsCount + ", id=" + id + ", name=" + name + ", terrain="
        + terrain + "]";
  }

}

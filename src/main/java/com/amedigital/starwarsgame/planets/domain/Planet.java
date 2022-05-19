package com.amedigital.starwarsgame.planets.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "planets")
public class Planet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "name is required")
  private String name;

  @NotEmpty(message = "climate is required")
  private String climate;

  @NotEmpty(message = "terrain is required")
  private String terrain;

  @NotNull(message = "filmsCount is required")
  private Integer filmsCount;

  public Planet() {
  }

  public Planet(String climate, String terrain, Integer filmsCount) {
    this.climate = climate;
    this.terrain = terrain;
    this.filmsCount = filmsCount;
  }

  public Planet(String name, String climate, String terrain, Integer filmsCount) {
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

  @Override
  public String toString() {
    return "Planet [Climate=" + climate + ", filmsCount=" + filmsCount + ", id=" + id + ", name=" + name + ", terrain="
        + terrain + "]";
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(obj, this);
  }

}

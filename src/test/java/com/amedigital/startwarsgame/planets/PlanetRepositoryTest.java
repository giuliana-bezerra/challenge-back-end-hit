package com.amedigital.startwarsgame.planets;

import static org.assertj.core.api.Assertions.assertThat;

import com.amedigital.startwarsgame.planets.domain.Planet;
import com.amedigital.startwarsgame.planets.domain.PlanetRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PlanetRepositoryTest {
  @Autowired
  private PlanetRepository planetRepository;

  @Test
  public void createUser_WithValidData_ReturnsUser() {
    Planet planet = new Planet("name", "climate", "terrain", 1);

    Planet created = planetRepository.save(planet);

    Planet response = planetRepository.findById(created.getId()).get();

    assertThat(response).isNotNull();
    assertThat(response.getName()).isEqualTo(planet.getName());
    assertThat(response.getClimate()).isEqualTo(planet.getClimate());
    assertThat(response.getTerrain()).isEqualTo(planet.getTerrain());
    assertThat(response.getFilmsCount()).isEqualTo(planet.getFilmsCount());
  }
}

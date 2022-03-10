package com.amedigital.startwarsgame.planets;

import static com.amedigital.startwarsgame.planets.PlanetsConstants.tatooine;
import static org.assertj.core.api.Assertions.assertThat;

import com.amedigital.startwarsgame.planets.domain.Planet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlanetIT {
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void createPlanet_returnsCreated() {
    Planet planet = new Planet("name", "climate", "terrain", 1);
    ResponseEntity<Planet> response = restTemplate.postForEntity("/planets", planet, Planet.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody().getId()).isNotNull();
    assertThat(response.getBody().getName()).isEqualTo(planet.getName());
    assertThat(response.getBody().getClimate()).isEqualTo(planet.getClimate());
    assertThat(response.getBody().getTerrain()).isEqualTo(planet.getTerrain());
    assertThat(response.getBody().getFilmsCount()).isEqualTo(planet.getFilmsCount());
  }

  @Test
  public void listPlanets_returnsPlanets() {
    ResponseEntity<Planet[]> response = restTemplate.getForEntity("/planets", Planet[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(3);

    Planet planet = response.getBody()[0];

    assertThat(planet.getId()).isNotNull();
    assertThat(planet.getName()).isEqualTo(tatooine.getName());
    assertThat(planet.getClimate()).isEqualTo(tatooine.getClimate());
    assertThat(planet.getTerrain()).isEqualTo(tatooine.getTerrain());
    assertThat(planet.getFilmsCount()).isEqualTo(tatooine.getFilmsCount());
  }
}

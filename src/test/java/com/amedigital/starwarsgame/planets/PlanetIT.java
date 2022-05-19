package com.amedigital.starwarsgame.planets;

import static com.amedigital.starwarsgame.planets.PlanetsConstants.TATOOINE;
import static org.assertj.core.api.Assertions.assertThat;

import com.amedigital.starwarsgame.planets.domain.Planet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlanetIT {
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void createPlanet_WithValidData_ReturnsCreated() {
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
  public void listPlanets_ReturnsAllPlanets() {
    ResponseEntity<Planet[]> response = restTemplate.getForEntity("/planets", Planet[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(3);

    Planet planet = response.getBody()[0];

    assertThat(planet.getId()).isNotNull();
    assertThat(planet.getName()).isEqualTo(TATOOINE.getName());
    assertThat(planet.getClimate()).isEqualTo(TATOOINE.getClimate());
    assertThat(planet.getTerrain()).isEqualTo(TATOOINE.getTerrain());
    assertThat(planet.getFilmsCount()).isEqualTo(TATOOINE.getFilmsCount());
  }

  @Test
  public void listPlanets_ByExistingClimate_ReturnsPlanets() {
    ResponseEntity<Planet[]> response = restTemplate.getForEntity("/planets?climate=" + TATOOINE.getClimate(),
        Planet[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(1);
    assertThat(response.getBody()[0].getName()).isEqualTo(TATOOINE.getName());
  }

  @Test
  public void listPlanets_ByUnexistingClimate_ReturnsEmpty() {
    ResponseEntity<Planet[]> response = restTemplate.getForEntity("/planets?climate=null",
        Planet[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(0);
  }

  @Test
  public void listPlanets_ByExistingTerrain_ReturnsPlanets() {
    ResponseEntity<Planet[]> response = restTemplate.getForEntity("/planets?terrain=" + TATOOINE.getTerrain(),
        Planet[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(1);
    assertThat(response.getBody()[0].getId()).isEqualTo(1L);
  }

  @Test
  public void listPlanets_ByUnexistingTerrain_ReturnsEmpty() {
    ResponseEntity<Planet[]> response = restTemplate.getForEntity("/planets?terrain=null",
        Planet[].class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(0);
  }

  @Test
  public void getPlanet_ByExistingId_ReturnsPlanet() {
    ResponseEntity<Planet> response = restTemplate.getForEntity("/planets/1", Planet.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isEqualTo(1L);
    assertThat(response.getBody().getName()).isEqualTo(TATOOINE.getName());
    assertThat(response.getBody().getClimate()).isEqualTo(TATOOINE.getClimate());
    assertThat(response.getBody().getTerrain()).isEqualTo(TATOOINE.getTerrain());
    assertThat(response.getBody().getFilmsCount()).isEqualTo(TATOOINE.getFilmsCount());
  }

  @Test
  public void getPlanet_ByUnexistingId_ReturnsNotFound() {
    ResponseEntity<Object> response = restTemplate.getForEntity("/planets/22", Object.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void getPlanet_ByExistingName_ReturnsPlanet() {
    ResponseEntity<Planet> response = restTemplate.getForEntity("/planets/name/" + TATOOINE.getName(), Planet.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isEqualTo(1L);
    assertThat(response.getBody().getName()).isEqualTo(TATOOINE.getName());
    assertThat(response.getBody().getClimate()).isEqualTo(TATOOINE.getClimate());
    assertThat(response.getBody().getTerrain()).isEqualTo(TATOOINE.getTerrain());
    assertThat(response.getBody().getFilmsCount()).isEqualTo(TATOOINE.getFilmsCount());
  }

  @Test
  public void getPlanet_ByUnexistingName_ReturnsNotFound() {
    ResponseEntity<Object> response = restTemplate.getForEntity("/planets/name/null", Object.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void removePlanet_WithExistingId_ReturnsOk() {
    ResponseEntity<Void> response = restTemplate.exchange("/planets/2", HttpMethod.DELETE, null, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void removePlanet_WithUnexistingId_ReturnsNotFound() {
    ResponseEntity<Void> response = restTemplate.exchange("/planets/22", HttpMethod.DELETE, null, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}

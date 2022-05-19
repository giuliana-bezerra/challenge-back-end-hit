package com.amedigital.starwarsgame.planets;

import static com.amedigital.starwarsgame.planets.PlanetsConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import java.util.Optional;

import com.amedigital.starwarsgame.common.repository.QueryBuilder;
import com.amedigital.starwarsgame.planets.domain.Planet;
import com.amedigital.starwarsgame.planets.domain.PlanetRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;

@DataJpaTest
public class PlanetRepositoryTest {
  @Autowired
  private PlanetRepository planetRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @AfterEach
  public void afterEach() {
    PLANET.setId(null);
  }

  @Test
  public void createUser_WithValidData_ReturnsUser() {
    Planet created = planetRepository.save(PLANET);
    Planet response = planetRepository.findById(created.getId()).get();

    assertThat(response).isNotNull();
    assertThat(response.getName()).isEqualTo(PLANET.getName());
    assertThat(response.getClimate()).isEqualTo(PLANET.getClimate());
    assertThat(response.getTerrain()).isEqualTo(PLANET.getTerrain());
    assertThat(response.getFilmsCount()).isEqualTo(PLANET.getFilmsCount());
  }

  @Test
  public void listPlanets_ReturnsAllPlanets() {
    Example<Planet> query = QueryBuilder.makeQuery(new Planet());

    List<Planet> response = planetRepository.findAll(query);

    assertThat(response).isNotEmpty();
    assertThat(response).hasSize(3);
  }

  @Test
  public void listPlanets_ReturnsNoPlanets() {
    Example<Planet> query = QueryBuilder.makeQuery(new Planet("Unexisting Climate", "Unexisting errain", -1));

    List<Planet> response = planetRepository.findAll(query);

    assertThat(response).isEmpty();
  }

  @Test
  public void listPlanets_ByExistingClimate_ReturnsPlanets() {
    Planet planet = testEntityManager.persistFlushFind(PLANET);
    Example<Planet> query = QueryBuilder.makeQuery(new Planet(planet.getClimate(), null, null));

    List<Planet> response = planetRepository.findAll(query);

    Planet planetResponse = response.get(0);
    assertThat(response).isNotEmpty();
    assertThat(response).hasSize(1);
    assertThat(planetResponse).isEqualTo(planet);
  }

  @Test
  public void listPlanets_ByUnexistingClimate_ReturnsEmpty() {
    Example<Planet> query = QueryBuilder.makeQuery(new Planet(PLANET.getClimate(), null, null));

    List<Planet> response = planetRepository.findAll(query);

    assertThat(response).isEmpty();
  }

  @Test
  public void listPlanets_ByExistingTerrain_ReturnsPlanets() {
    Planet planet = testEntityManager.persistFlushFind(PLANET);
    Example<Planet> query = QueryBuilder.makeQuery(new Planet(null, planet.getTerrain(), null));

    List<Planet> response = planetRepository.findAll(query);

    Planet planetResponse = response.get(0);
    assertThat(response).isNotEmpty();
    assertThat(response).hasSize(1);
    assertThat(planetResponse).isEqualTo(planet);
  }

  @Test
  public void listPlanets_ByUnexistingTerrain_ReturnsEmpty() {
    Example<Planet> query = QueryBuilder.makeQuery(new Planet(null, PLANET.getTerrain(), null));

    List<Planet> response = planetRepository.findAll(query);

    assertThat(response).isEmpty();
  }

  @Test
  public void getPlanet_ByExistingId_ReturnsPlanet() {
    Planet planet = testEntityManager.persistFlushFind(PLANET);

    Optional<Planet> planetOpt = planetRepository.findById(planet.getId());

    assertThat(planetOpt).isNotEmpty();
    assertThat(planetOpt.get()).isEqualTo(planet);
  }

  @Test
  public void getPlanet_ByUnexistingId_ReturnsEmpty() {
    Optional<Planet> planetOpt = planetRepository.findById(99L);

    assertThat(planetOpt).isEmpty();
  }

  @Test
  public void getPlanet_ByExistingName_ReturnsPlanet() {
    Planet planet = testEntityManager.persistFlushFind(PLANET);

    Optional<Planet> planetOpt = planetRepository.findByName(planet.getName());

    assertThat(planetOpt).isNotEmpty();
    assertThat(planetOpt.get()).isEqualTo(planet);
  }

  @Test
  public void getPlanet_ByUnexistingName_ReturnsNotFound() {
    Optional<Planet> planetOpt = planetRepository.findByName(PLANET.getName());

    assertThat(planetOpt).isEmpty();
  }

  @Test
  public void removePlanet_WithExistingId_RemovesPlanetFromDatabase() {
    Planet planet = testEntityManager.persistFlushFind(PLANET);

    planetRepository.deleteById(planet.getId());

    Planet removedPlanet = testEntityManager.find(Planet.class, planet.getId());
    assertThat(removedPlanet).isNull();
  }

  @Test
  public void removePlanet_WithUnexistingId_RemovesNothing() {
    assertThatCode(() -> planetRepository.deleteById(1L)).doesNotThrowAnyException();
  }
}

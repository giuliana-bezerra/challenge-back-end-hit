package com.amedigital.starwarsgame.planets;

import static com.amedigital.starwarsgame.planets.PlanetsConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amedigital.starwarsgame.common.repository.QueryBuilder;
import com.amedigital.starwarsgame.planets.domain.Planet;
import com.amedigital.starwarsgame.planets.domain.PlanetRepository;
import com.amedigital.starwarsgame.planets.domain.PlanetService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {
  @InjectMocks
  private PlanetService planetService;

  @Mock
  private PlanetRepository planetRepository;

  @Test
  public void createPlanet_WithValidData_ReturnsPlanet() {
    when(planetRepository.save(PLANET)).thenReturn(PLANET);

    Planet response = planetService.create(PLANET);

    verify(planetRepository, times(1)).save(PLANET);
    assertThat(response).isNotNull();
    assertThat(response).isEqualTo(PLANET);
  }

  @Test
  public void listPlanets_ReturnsAllPlanets() {
    List<Planet> planets = new ArrayList<>() {
      {
        add(PLANET);
      }
    };
    Example<Planet> query = QueryBuilder.makeQuery(new Planet());

    when(planetRepository.findAll(query)).thenReturn(planets);

    List<Planet> response = planetService.list(null, null, null);
    assertThat(response).isNotEmpty();
    assertThat(response).hasSize(1);
    assertThat(response.get(0)).isEqualTo(PLANET);
  }

  @Test
  public void listPlanets_ReturnsNoPlanets() {
    when(planetRepository.findAll(any())).thenReturn(Collections.emptyList());

    List<Planet> response = planetService.list(PLANET.getTerrain(), PLANET.getClimate(), PLANET.getFilmsCount());
    assertThat(response).isEmpty();
  }

  @Test
  public void getPlanet_ByExistingId_ReturnsPlanet() {
    when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));

    Optional<Planet> response = planetService.get(1L);

    assertThat(response).isNotEmpty();
    assertThat(response.get()).isEqualTo(PLANET);
  }

  @Test
  public void getPlanet_ByUnexistingId_ReturnsEmpty() {
    when(planetRepository.findById(anyLong())).thenReturn(Optional.empty());

    Optional<Planet> response = planetService.get(1L);

    assertThat(response).isEmpty();
  }

  @Test
  public void getPlanet_ByExistingName_ReturnsPlanet() {
    when(planetRepository.findByName(anyString())).thenReturn(Optional.of(PLANET));

    Optional<Planet> response = planetService.getByName(PLANET.getName());

    assertThat(response).isNotEmpty();
    assertThat(response.get()).isEqualTo(PLANET);
  }

  @Test
  public void getPlanet_ByUnexistingName_ReturnsEmpty() {
    when(planetRepository.findByName(anyString())).thenReturn(Optional.empty());

    Optional<Planet> response = planetService.getByName(PLANET.getName());

    assertThat(response).isEmpty();
  }

  @Test
  public void removePlanet_WithExistingId_doesNotThrowAnyException() {
    assertThatCode(() -> planetService.remove(1L)).doesNotThrowAnyException();
  }

  @Test
  public void removePlanet_WithUnexistingId_ThrowsException() {
    doThrow(new EmptyResultDataAccessException(1)).when(planetRepository).deleteById(99L);

    assertThatThrownBy(() -> planetService.remove(99L)).isInstanceOf(EmptyResultDataAccessException.class);
  }
}

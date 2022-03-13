package com.amedigital.startwarsgame.planets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.amedigital.startwarsgame.planets.domain.Planet;
import com.amedigital.startwarsgame.planets.domain.PlanetRepository;
import com.amedigital.startwarsgame.planets.domain.PlanetService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {
  @InjectMocks
  private PlanetService planetService;

  @Mock
  private PlanetRepository planetRepository;

  @Test
  public void createPlanet_WithValidData_ReturnsPlanet() {
    Planet planet = new Planet("name", "climate", "terrain", 1);

    when(planetRepository.save(any())).thenReturn(planet);

    Planet response = planetService.create(planet);

    verify(planetRepository, times(1)).save(planet);
    assertThat(response).isNotNull();
    assertThat(response).isEqualTo(planet);
  }
}

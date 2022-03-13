package com.amedigital.startwarsgame.planets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amedigital.startwarsgame.planets.domain.Planet;
import com.amedigital.startwarsgame.planets.domain.PlanetService;
import com.amedigital.startwarsgame.planets.web.PlanetController;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objMapper;

  @MockBean
  private PlanetService planetService;

  @Test
  public void createPlanet_WithValidData_ReturnsCreated() throws Exception {
    Planet planet = new Planet("name", "climate", "terrain", 1);

    when(planetService.create(planet)).thenReturn(planet);

    mockMvc
        .perform(post("/planets").content(objMapper.writeValueAsString(planet)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value(planet.getName()))
        .andExpect(jsonPath("$.climate").value(planet.getClimate()))
        .andExpect(jsonPath("$.terrain").value(planet.getTerrain()))
        .andExpect(jsonPath("$.filmsCount").value(planet.getFilmsCount()));
  }
}

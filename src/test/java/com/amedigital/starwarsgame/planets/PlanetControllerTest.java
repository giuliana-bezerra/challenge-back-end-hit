package com.amedigital.starwarsgame.planets;

import static com.amedigital.starwarsgame.planets.PlanetsConstants.PLANET;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.amedigital.starwarsgame.planets.domain.Planet;
import com.amedigital.starwarsgame.planets.domain.PlanetService;
import com.amedigital.starwarsgame.planets.web.PlanetController;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
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
    when(planetService.create(any())).thenReturn(PLANET);

    mockMvc
        .perform(post("/planets").content(objMapper.writeValueAsString(PLANET)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").value(PLANET));
  }

  @Test
  public void createPlanet_WithInvalidData_ReturnsBadRequest() throws Exception {
    Planet emptyPlanet = new Planet(null, null, null, null);
    Planet invalidPlanet = new Planet("", "", "", null);

    mockMvc
        .perform(
            post("/planets").content(objMapper.writeValueAsString(emptyPlanet)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(jsonPath("$.message").value("validation failed"))
        .andExpect(jsonPath("$.details", hasSize(4)));
    mockMvc
        .perform(
            post("/planets").content(objMapper.writeValueAsString(invalidPlanet))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(jsonPath("$.message").value("validation failed"))
        .andExpect(jsonPath("$.details", hasSize(4)));
  }

  @Test
  public void listPlanets_ReturnsAllPlanets() throws Exception {
    List<Planet> planets = new ArrayList<>() {
      {
        add(PLANET);
      }
    };

    when(planetService.list(null, null, null)).thenReturn(planets);

    mockMvc
        .perform(get("/planets"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  public void listPlanets_ReturnsNoPlanets() throws Exception {
    when(planetService.list(null, null, null)).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/planets"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void listPlanets_ByExistingClimate_ReturnsPlanets() throws Exception {
    List<Planet> planets = new ArrayList<>() {
      {
        add(PLANET);
      }
    };

    when(planetService.list(null, PLANET.getClimate(), null)).thenReturn(planets);

    mockMvc
        .perform(get("/planets?climate=" + PLANET.getClimate()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0]").value(PLANET));
  }

  @Test
  public void listPlanets_ByUnexistingClimate_ReturnsPlanets() throws Exception {
    mockMvc
        .perform(get("/planets?climate=" + null))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void listPlanets_ByExistingTerrain_ReturnsPlanets() throws Exception {
    List<Planet> planets = new ArrayList<>() {
      {
        add(PLANET);
      }
    };

    when(planetService.list(PLANET.getTerrain(), null, null)).thenReturn(planets);

    mockMvc
        .perform(get("/planets?terrain=" + PLANET.getTerrain()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0]").value(PLANET));
  }

  @Test
  public void listPlanets_ByUnexistingTerrain_ReturnsPlanets() throws Exception {
    mockMvc
        .perform(get("/planets?terrain=" + null))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  public void getPlanet_ByExistingId_ReturnsPlanet() throws Exception {
    when(planetService.get(1L)).thenReturn(Optional.of(PLANET));

    mockMvc
        .perform(get("/planets/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(PLANET));
  }

  @Test
  public void getPlanet_ByUnexistingId_ReturnsNotFound() throws Exception {
    mockMvc
        .perform(get("/planets/1"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void getPlanet_ByExistingName_ReturnsPlanet() throws Exception {
    when(planetService.getByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

    mockMvc
        .perform(get("/planets/name/" + PLANET.getName()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(PLANET));
  }

  @Test
  public void getPlanet_ByUnexistingName_ReturnsNotFound() throws Exception {
    mockMvc
        .perform(get("/planets/name/name"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void removePlanet_WithExistingId_ReturnsOk() throws Exception {
    mockMvc
        .perform(delete("/planets/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void removePlanet_WithUnexistingId_ReturnsNotFound() throws Exception {
    doThrow(new EmptyResultDataAccessException(1)).when(planetService).remove(1L);

    mockMvc
        .perform(delete("/planets/1"))
        .andExpect(status().isNotFound());
  }
}

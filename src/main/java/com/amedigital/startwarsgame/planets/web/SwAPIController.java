package com.amedigital.startwarsgame.planets.web;

import java.util.List;

import com.amedigital.startwarsgame.planets.api.PlanetResponse;
import com.amedigital.startwarsgame.planets.api.SwAPIResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/swapi/planets")
public class SwAPIController {
  @Value("${swapi.url}")
  private String url;
  private RestTemplate restTemplate = new RestTemplate();

  @GetMapping
  public ResponseEntity<List<PlanetResponse>> list(@RequestParam(required = false) String search) {
    String resourceUri = getSearchForPlanetsUri(search);
    ResponseEntity<SwAPIResponse> response = restTemplate.getForEntity(resourceUri, SwAPIResponse.class);

    return ResponseEntity.status(response.getStatusCode()).body(response.getBody().getResults());
  }

  private String getSearchForPlanetsUri(String search) {
    return url + "/planets" + (search != null ? "?search=" + search : "");
  }

}

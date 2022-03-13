package com.amedigital.startwarsgame.planets.api;

import java.util.List;

public class SwAPIResponse {
  private List<PlanetResponse> results;

  public SwAPIResponse() {
  }

  public List<PlanetResponse> getResults() {
    return results;
  }

  public void setResults(List<PlanetResponse> results) {
    this.results = results;
  }
}

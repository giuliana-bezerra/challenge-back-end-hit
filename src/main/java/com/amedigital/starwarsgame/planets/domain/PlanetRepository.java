package com.amedigital.starwarsgame.planets.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PlanetRepository extends CrudRepository<Planet, Long>, QueryByExampleExecutor<Object> {
  @Override
  <S> List<S> findAll(Example<S> example);

  Optional<Planet> findByName(String name);
}

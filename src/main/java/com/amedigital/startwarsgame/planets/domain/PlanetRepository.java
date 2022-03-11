package com.amedigital.startwarsgame.planets.domain;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PlanetRepository extends CrudRepository<Planet, Long>, QueryByExampleExecutor<Object> {
  @Override
  <S> List<S> findAll(Example<S> example);
}

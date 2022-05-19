package com.amedigital.starwarsgame.common.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder<T> {
  public static <T> Example<T> makeQuery(T obj) {
    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
    Example<T> query = Example.of(obj, exampleMatcher);
    return query;
  }
}

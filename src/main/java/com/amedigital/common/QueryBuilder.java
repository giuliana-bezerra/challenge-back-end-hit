package com.amedigital.common;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder<T> {
  public static <T> Example<T> makeQuery(T obj) {
    ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
    Example<T> query = Example.of(obj, caseInsensitiveExampleMatcher);
    return query;
  }
}

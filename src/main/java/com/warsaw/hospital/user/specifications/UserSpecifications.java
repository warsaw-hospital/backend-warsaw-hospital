package com.warsaw.hospital.user.specifications;

import com.warsaw.hospital.user.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpecifications {
  public static Specification<UserEntity> hasNotNullCreationDate() {
    return (root, query, cb) -> cb.isNotNull(root.get("creation"));
  }

  public static Specification<UserEntity> filterBySearch(String search) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      List<String> words =
          Arrays.stream(search.split(" "))
              .map(word -> "%" + word + "%")
              .collect(Collectors.toList());

      for (String word : words) {
        predicates.add(cb.like(cb.lower(root.get("name")), word.toLowerCase()));
        predicates.add(cb.like(cb.lower(root.get("surname")), word.toLowerCase()));
        predicates.add(cb.like(cb.lower(root.get("email")), word.toLowerCase()));
        predicates.add(cb.like(root.get("personalCode"), word));
        predicates.add(cb.like(root.get("phoneNumber"), word));
        predicates.add(cb.like(root.get("mainEvrkCode"), word));
      }

      return cb.or(predicates.toArray(new Predicate[0]));
    };
  }

  private static Subquery<Long> declarationCountQuery(
      CriteriaQuery<?> query, CriteriaBuilder cb, Expression<Long> userId) {
    Subquery<Long> subQuery = query.subquery(Long.class);
    Root<UserEntity> root = subQuery.from(UserEntity.class);
    subQuery
        .select(cb.countDistinct(root.join("declarations").get("id")))
        .where(cb.equal(root.get("id"), userId));
    return subQuery;
  }

  private static Subquery<Long> accommodationCountQuery(
      CriteriaQuery<?> query, CriteriaBuilder cb, Expression<Long> userId) {
    Subquery<Long> subQuery = query.subquery(Long.class);
    Root<UserEntity> root = subQuery.from(UserEntity.class);
    subQuery
        .select(cb.countDistinct(root.join("objects").join("accommodations")))
        .where(cb.equal(root.get("id"), userId));
    return subQuery;
  }

  private static Subquery<Timestamp> oldestAccommodationDateQuery(
      CriteriaQuery<?> query, CriteriaBuilder cb) {
    Subquery<Timestamp> subQuery = query.subquery(Timestamp.class);
    Root<UserEntity> root = subQuery.from(UserEntity.class);
    subQuery.select(
        cb.least(root.join("objects").join("accommodations").get("fromDate").as(Timestamp.class)));
    return subQuery;
  }
}

package com.warsaw.hospital.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import java.sql.Timestamp;
import java.util.List;

public class SpecificationUtil {
  public static <T> Specification<T> toANDSpecification(List<Specification<T>> specifications) {
    if (specifications.size() == 1) {
      return specifications.get(0);
    } else {
      Specification<T> finalSpecification = null;
      for (int i = 0; i < specifications.size(); i++) {
        if (i == 0) {
          finalSpecification = specifications.get(i);
        } else {
          finalSpecification = finalSpecification.and(specifications.get(i));
        }
      }
      return finalSpecification;
    }
  }

  public static Expression<Long> getDatePart(
      CriteriaBuilder cb, String part, Expression<Timestamp> timestamp) {
    // In PostgresSQL: date_part(part, timestamp)
    // Here part can be: year, month, day, hour, minute, second
    // and timestamp is a valid timestamp
    return cb.function("date_part", Long.class, cb.literal(part), timestamp);
  }

  public static Expression<Long> getMonthDifference(
      CriteriaBuilder cb, Expression<Timestamp> from, Expression<Timestamp> to) {
    Expression<Long> year1 = getDatePart(cb, "year", from);
    Expression<Long> month1 = getDatePart(cb, "month", from);
    Expression<Long> year2 = getDatePart(cb, "year", to);
    Expression<Long> month2 = getDatePart(cb, "month", to);
    // |year2 - year1| * 12 + |month2 - month1|
    return cb.sum(cb.prod(cb.diff(year2, year1), 12L), cb.diff(month2, month1));
  }
}

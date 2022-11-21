package com.warsaw.hospital.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateUtil {
  public static LocalDate toFirstDayOfMonth(LocalDate date) {
    return LocalDateUtil.toLocalDate(date, 1);
  }

  public static LocalDate toFirstDayOfMonth(LocalDateTime date) {
    return LocalDateUtil.toLocalDate(date, 1);
  }

  public static LocalDate toLocalDate(LocalDateTime date, int day) {
    return LocalDate.of(date.getYear(), date.getMonthValue(), day);
  }

  public static LocalDate toLocalDate(LocalDate date, int day) {
    return LocalDate.of(date.getYear(), date.getMonthValue(), day);
  }

  public static int getDayCountBetween(LocalDate from, LocalDate to) {
    return (int) ChronoUnit.DAYS.between(from, to);
  }
}

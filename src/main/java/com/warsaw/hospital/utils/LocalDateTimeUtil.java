package com.warsaw.hospital.utils;

import io.micrometer.core.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LocalDateTimeUtil {
  public static int getDayCountBetween(LocalDate from, LocalDate to) {
    return LocalDateUtil.getDayCountBetween(from, to);
  }

  public static int getHourCountFromDateToNow(LocalDate from) {
    return (int) ChronoUnit.HOURS.between(from, LocalDateTime.now());
  }

  public static int getHourCountFromDateToNow(LocalDateTime from) {
    return (int) ChronoUnit.HOURS.between(from, LocalDateTime.now());
  }

  public static int getDaysInPeriodIn(LocalDate from, LocalDate to, Integer month) {
    if (from.getMonthValue() == to.getMonthValue() && from.getMonthValue() == month) {
      return (int) ChronoUnit.DAYS.between(from, to);
    } else if (from.getMonthValue() < month && to.getMonthValue() > month) {
      return YearMonth.of(from.getYear(), month).lengthOfMonth();
    } else if (to.getMonthValue() == month) {
      return to.getDayOfMonth() - 1;
    } else if (from.getMonthValue() == month) {
      return YearMonth.of(from.getYear(), month).lengthOfMonth() - from.getDayOfMonth() + 1;
    } else {
      return 0;
    }
  }

  public static Integer getDaysInPeriodIn(
      LocalDate from, LocalDate to, Integer year, Integer month) {
    if (from.getYear() == year || to.getYear() == year) {
      return getDaysInPeriodIn(from, to, month);
    } else {
      return 0;
    }
  }

  public static LocalDate findOldest(@Nullable LocalDate date1, @NotNull LocalDate date2) {
    if (date1 != null && date1.isBefore(date2)) {
      return date1;
    } else {
      return date2;
    }
  }

  public static LocalDate findOldest(@Nullable LocalDate date1, @NotNull LocalDateTime date2) {
    if (date1 != null && date1.isBefore(date2.toLocalDate())) {
      return date1;
    } else {
      return date2.toLocalDate();
    }
  }

  public static List<LocalDate> getAllDatesIn(@NotNull Integer year, @NotNull Integer month) {
    var dates = new ArrayList<LocalDate>();

    LocalDate date = LocalDate.of(year, month, 1);
    while (date.getMonthValue() == month) {
      dates.add(date);
      date = date.plusDays(1);
    }
    return dates;
  }

  public static LocalDate getFirstDayOfMonth(Integer year, Integer month) {
    return LocalDate.of(year, month, 1);
  }

  public static LocalDate getLastMonthDay(Integer year, Integer month) {
    return LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
  }
}

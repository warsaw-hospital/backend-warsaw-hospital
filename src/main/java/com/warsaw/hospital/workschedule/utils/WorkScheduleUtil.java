package com.warsaw.hospital.workschedule.utils;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.basics.date.HolidayCalendar;
import com.opengamma.strata.basics.date.HolidayCalendarId;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class WorkScheduleUtil {
  public List<LocalDate> getAllDatesThreeMonthsFromNow() {
    LocalDate now = LocalDate.now();
    return now.datesUntil(now.plusMonths(3)).collect(Collectors.toList());
  }

  public static List<LocalDate> getAllDatesByWeekDayThreeMonthsFromNow(DayOfWeek dayOfWeek) {
    LocalDate now = LocalDate.now();
    return now.datesUntil(now.plusMonths(3))
        .filter(date -> dayOfWeek.equals(date.getDayOfWeek()))
        .collect(Collectors.toList());
  }

}

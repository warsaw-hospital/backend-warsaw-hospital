package com.warsaw.hospital.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilFunctions {
  public static String formattedDate(LocalDateTime dateTime) {
    String dateString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String timeString = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    return dateString + "T" + timeString;
  }

  public static String formattedDate(LocalDate dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}

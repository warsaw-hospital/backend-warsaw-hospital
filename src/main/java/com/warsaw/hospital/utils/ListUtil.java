package com.warsaw.hospital.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
  /**
   * This method casts object to an arraylist of the specified class. It returns null if object is
   * not an instance of a list.
   *
   * @param maybeList object which might contain a list.
   * @param elementClass expected class of list elements.
   * @return List of specified class or null.
   */
  public static <T> List<T> objectToList(Object maybeList, Class<T> elementClass) {
    List<T> result = null;
    if (maybeList instanceof List<?>) {
      result = new ArrayList<>();
      for (Object element : (List<?>) maybeList) {
        result.add(elementClass.cast(element));
      }
    }
    return result;
  }
}

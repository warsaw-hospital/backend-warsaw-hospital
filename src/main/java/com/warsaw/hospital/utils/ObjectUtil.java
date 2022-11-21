package com.warsaw.hospital.utils;

import io.micrometer.core.lang.Nullable;

public class ObjectUtil {
    public static String valueOrEmptyString(@Nullable Object value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }
}

package com.warsaw.hospital.auth;

import com.warsaw.hospital.user.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class AuthEmailUtil {
  private static final String WELCOME_EMAIL_TEMPLATE = "WELCOME";
  private static final String USER_NAME = "NAME";
  private static final String USER_LASTNAME = "LASTNAME";
  private static final String USER_EMAIL = "EMAIL";

  /**
   * This method returns data map for representing person email.
   *
   * @param entity representing person entity.
   * @return map for representing person email.
   */
  public static Map<String, String> getDataForEmail(UserEntity entity) {
    Map<String, String> data = new HashMap<>();
    data.put(USER_NAME, entity.getName());
    data.put(USER_LASTNAME, entity.getLastname());
    data.put(USER_EMAIL, entity.getName());
    return data;
  }

  /**
   * This method returns email template name for user welcoming email.
   *
   * @return email template name
   */
  public static String getEmailTemplateName() {
    return WELCOME_EMAIL_TEMPLATE;
  }
}

package com.warsaw.hospital.emailtemplate.util;

import com.warsaw.hospital.exception.ApiException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class StringTemplateManager {
  private static final String TEMPLATE_START_TOKEN = "%{{";
  private static final String TEMPLATE_CLOSE_TOKEN = "}}%";

  private String text;
  private Map<String, String> data = new HashMap<>();

  public String getText() {
    return text;
  }

  /**
   * This method sets the text field, which will be parsed. Also, this method throws exception, when
   * text is null, blank or empty.
   *
   * @param text text to be parsed.
   * @return this.
   * @throws ApiException when text is null, blank or empty.
   */
  public StringTemplateManager setText(String text) throws ApiException {
    if (text == null || text.isEmpty() || text.isBlank()) {
      throw ApiException.bad("err.StringTemplateManager.invalidText").addLabel("text", text);
    }
    this.text = text;
    return this;
  }

  public Map<String, String> getData() {
    return data;
  }

  /**
   * This method sets data field. Also, an api exception is thrown, when data map is empty.
   *
   * @param data data map.
   * @return this.
   * @throws ApiException when data map is empty.
   */
  public StringTemplateManager setData(Map<String, String> data) throws ApiException {
    if (data.isEmpty()) {
      throw ApiException.bad("err.StringTemplateManager.invalidData")
          .addLabel("data", data.toString());
    }
    this.data = data;
    return this;
  }

  /**
   * This method parses text field and uses data map to input required values into their places.
   *
   * @return the text with all values placed in the required places.
   * @throws ApiException when there is an unknown key in data.
   */
  public String process() throws ApiException {
    StringBuilder result = new StringBuilder();
    int startIndex = 0;
    while (startIndex < text.length()) {
      // Find the index of the starting brackets ${{
      int openIndex = text.indexOf(TEMPLATE_START_TOKEN, startIndex);
      if (openIndex < 0) {
        result.append(text.substring(startIndex));
        break;
      }

      // Find the index of the closing brackets }}$
      int closeIndex = text.indexOf(TEMPLATE_CLOSE_TOKEN, openIndex);
      if (closeIndex < 0) {
        result.append(text.substring(startIndex));
        break;
      }

      // Get key value between starting and closing brackets
      String key = text.substring(openIndex + TEMPLATE_START_TOKEN.length(), closeIndex);
      if (!data.containsKey(key)) {
        throw ApiException.bad("err.StringTemplateManager.unknownKey").addLabel("key", key);
      }

      // Append text from last startIndex (parsed character) to opening brackets
      result.append(text, startIndex, openIndex);
      // Append the value from map by using key value
      result.append(data.get(key));
      // Change start index value to the end of closing brackets
      startIndex = closeIndex + TEMPLATE_CLOSE_TOKEN.length();
    }
    return result.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StringTemplateManager)) return false;
    StringTemplateManager that = (StringTemplateManager) o;
    return getText().equals(that.getText()) && getData().equals(that.getData());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getText(), getData());
  }
}

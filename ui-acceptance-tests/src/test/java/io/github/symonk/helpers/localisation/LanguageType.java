package io.github.symonk.helpers.localisation;

import org.openqa.selenium.InvalidArgumentException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public enum LanguageType implements ParsesProperties {
    
  ENGLISH {
    @Override
    public Map<String, String> parsePropertiesAsMap() {
      return getResources("english");
    }
  },
  SPANISH {
    @Override
    public Map<String, String> parsePropertiesAsMap() {
      return getResources("spanish");
    }
  };

  public Map<String, String> getResources(String language) {
    Properties tempProperties = new Properties();
    Map<String, String> mappedProperties = new HashMap<>();
    try (InputStream inputStream = LanguageType.class.getResourceAsStream("/localisation/" + language + ".properties")) {
      tempProperties.load(inputStream);
    } catch (IOException e) {
      throw new InvalidArgumentException("Language specified at runtime is not supported!");
    }
    return tempProperties
        .entrySet()
        .stream()
        .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
  }
}

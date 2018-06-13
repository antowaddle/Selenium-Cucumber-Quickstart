package io.github.anthony.helpers.localisation;

import io.github.anthony.common.exceptions.InvalidLanguageSpecifiedException;

import java.io.IOException;
import java.io.InputStream;
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
    try (InputStream inputStream = LanguageType.class.getResourceAsStream("/localisation_files/" + language + ".properties")) {
      tempProperties.load(inputStream);
    } catch (IOException e) {
      throw new InvalidLanguageSpecifiedException("Language specified at runtime is not supported!");
    }
    return tempProperties
        .entrySet()
        .stream()
        .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
  }
}

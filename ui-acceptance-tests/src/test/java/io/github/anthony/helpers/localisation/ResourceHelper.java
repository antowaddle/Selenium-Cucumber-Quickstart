package io.github.anthony.helpers.localisation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public class ResourceHelper implements ReadsResources {

  private LanguageType language;
  private Map<String, String> resources;

  @Autowired
  public ResourceHelper(LanguageType language) {
    resources = language.parsePropertiesAsMap();
  }

  @Override
  public String getResourceValue(String resourceKey) {
    return resources.get(resourceKey);
  }



}

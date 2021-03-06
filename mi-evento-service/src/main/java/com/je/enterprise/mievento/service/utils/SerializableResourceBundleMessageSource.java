package com.je.enterprise.mievento.service.utils;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class SerializableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource{

	  public Properties getAllProperties(Locale locale) {
	        clearCacheIncludingAncestors();
	        PropertiesHolder propertiesHolder = getMergedProperties(locale);
	        Properties properties = propertiesHolder.getProperties();
	 
	        return properties;
	    }
}

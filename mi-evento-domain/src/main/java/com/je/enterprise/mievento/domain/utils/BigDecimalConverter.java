package com.je.enterprise.mievento.domain.utils;

import java.math.BigDecimal;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.MappingException;

public class BigDecimalConverter extends TypeConverter implements SimpleValueConverter {

	public BigDecimalConverter() {
		super(BigDecimal.class);
	}

	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		if(value == null){
			return null;
		}
		return value.toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object decode(Class targetClass, Object fromDBObject, MappedField optionalExtraInfo) throws MappingException{
		if(fromDBObject == null){
			return null;
		}
		return new BigDecimal(fromDBObject.toString());
	}
}
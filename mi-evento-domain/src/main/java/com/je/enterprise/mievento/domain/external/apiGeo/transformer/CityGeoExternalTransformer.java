package com.je.enterprise.mievento.domain.external.apiGeo.transformer;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOExternalWrapper;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class CityGeoExternalTransformer extends
		Transformer<CityEntity, GEOExternalWrapper> {
	
	private static final String PART_DE = "Partido de ";
	private static final String DEPT_DE = "Departamento de ";
	private static final String DEPT_DEL = "Departamento del ";
	private static final String MUN_DE = "Municipio de ";
	private static final String MUN = "Municipio ";
	private static final String COUN = "Country ";
	
	@Override
	protected GEOExternalWrapper transformDomainToApi(CityEntity domainObject) {
		return null;
	}

	@Override
	protected CityEntity transformApiToDomain(GEOExternalWrapper apiObject) {
		
		String newName = StringUtils.delete(apiObject.getName(), PART_DE);
		newName = StringUtils.delete(newName, DEPT_DEL);
		newName = StringUtils.delete(newName, DEPT_DE);
		newName = StringUtils.delete(newName, MUN_DE);
		newName = StringUtils.delete(newName, MUN);
		newName = StringUtils.delete(newName, COUN);
		
		return new CityEntity(newName, apiObject.getLat(), apiObject.getLng());
	}

}

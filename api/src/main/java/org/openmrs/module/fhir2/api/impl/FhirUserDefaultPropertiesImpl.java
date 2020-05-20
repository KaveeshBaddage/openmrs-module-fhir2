/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.fhir2.api.impl;

import java.util.Locale;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openmrs.module.fhir2.api.FhirGlobalPropertyService;
import org.openmrs.module.fhir2.api.FhirUserDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@Setter(AccessLevel.PACKAGE)
public class FhirUserDefaultPropertiesImpl implements FhirUserDefaultProperties {
	
	@Autowired
	private FhirGlobalPropertyService globalPropertyService;
	
	@Override
	public Locale getDefaultLocale() {
		// TODO find a way of getting userLocale without relying on context
		// return Context.getLocale();
		String locale = globalPropertyService.getGlobalProperty("default_locale", "en_GB");
		if (locale.contains("_")) {
			String[] langRegion = locale.split("_");
			log.info("Language :" + langRegion[0]);
			log.info("Region :" + langRegion[1]);
			return new Locale.Builder().setLanguage(langRegion[0]).setRegion(langRegion[1]).build();
		}
		return new Locale.Builder().setLanguage(locale).build();
	}
}

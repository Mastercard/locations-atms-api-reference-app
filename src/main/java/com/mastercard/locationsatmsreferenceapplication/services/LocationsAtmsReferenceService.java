package com.mastercard.locationsatmsreferenceapplication.services;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.AtmSearch;
import org.openapitools.client.model.Atms;
import org.openapitools.client.model.Countries;
import org.openapitools.client.model.CountrySubdivisions;
import org.springframework.stereotype.Service;

@Service
public interface LocationsAtmsReferenceService {

    Countries getCountries() throws ApiException;

    CountrySubdivisions getCountrySubdivisions(String countryCode)  throws ApiException;

    Atms getAtms(AtmSearch atmSearch, Integer limit, Integer offset, Integer distance, String distanceUnit)  throws ApiException;
}

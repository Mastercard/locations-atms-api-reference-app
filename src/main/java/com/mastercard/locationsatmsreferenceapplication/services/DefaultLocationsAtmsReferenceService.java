package com.mastercard.locationsatmsreferenceapplication.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.AtmsApi;
import org.openapitools.client.api.CountriesApi;
import org.openapitools.client.model.AtmSearch;
import org.openapitools.client.model.Atms;
import org.openapitools.client.model.Countries;
import org.openapitools.client.model.CountrySubdivisions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultLocationsAtmsReferenceService implements LocationsAtmsReferenceService {


    private static final Logger logger = LoggerFactory.getLogger(DefaultLocationsAtmsReferenceService.class);


    private CountriesApi countriesListApi;
    private AtmsApi atmsApi;


    @Autowired
    public DefaultLocationsAtmsReferenceService(ApiClient apiClient) {
        logger.info("-->> INITIALIZING APIS");

        countriesListApi = new CountriesApi(apiClient);
        atmsApi = new AtmsApi(apiClient);

    }
    @Override
    public Countries getCountries()  throws ApiException {
            return countriesListApi.getCountries();
    }

    @Override
    public CountrySubdivisions getCountrySubdivisions(String countryCode) throws ApiException {
            return  countriesListApi.getCountrySubdivisions(countryCode);
    }

    @Override
    public Atms getAtms(AtmSearch atmSearch, Integer limit, Integer offset, Integer distance, String distanceUnit)  throws ApiException{

        return atmsApi.getAtms(atmSearch, limit, offset, distance, distanceUnit);
    }

    public String getErrorAttributes(Exception e) {
        String errorDetails;
        if(e instanceof ApiException) {
            ApiException apiException = ((ApiException) e);

            //Attempt to parse as JSON
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                errorDetails = gson.toJson(JsonParser.parseString(apiException.getResponseBody()));

            } catch (Exception ignoredException) {

                //Print full string in case of not JSON
                errorDetails = apiException.getResponseBody();
            }

        } else {

            logger.error("Error occurred!",e);
            errorDetails = e.toString();

        }

        return errorDetails;
    }


}

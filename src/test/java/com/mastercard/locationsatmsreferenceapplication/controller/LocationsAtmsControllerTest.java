package com.mastercard.locationsatmsreferenceapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.locationsatmsreferenceapplication.services.LocationsAtmsReferenceService;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.AtmSearch;
import org.openapitools.client.model.Atms;
import org.openapitools.client.model.Countries;
import org.openapitools.client.model.Country;
import org.openapitools.client.model.CountrySubdivision;
import org.openapitools.client.model.CountrySubdivisions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LocationsAtmsController.class)
    public class LocationsAtmsControllerTest {

    @MockBean
    private LocationsAtmsReferenceService locationsAtmsReferenceService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @org.junit.Test
    public void testGetCountries_successfulRequest() throws Exception {
        Countries mockedReturn = new Countries();
        Country country = new Country();
        country.countryCode("USA");
        List list = new ArrayList();
        list.add(country);
        mockedReturn.setCountries(list);

        given(locationsAtmsReferenceService.getCountries()).willReturn(mockedReturn);

        mvc.perform(get("/locations/atms/countries")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.countries[0].countryCode").value("USA"));
    }

    @org.junit.Test
    public void testCountries_ErrorHandling() throws Exception {
        doThrow(new ApiException(400, "Something went wrong")).when(locationsAtmsReferenceService).getCountries();
        mvc.perform(get("/locations/atms/countries")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Something went wrong"));
    }

    @org.junit.Test
    public void testGetCountrySubdivisions_successfulRequest() throws Exception {
        CountrySubdivisions mockedReturn = new CountrySubdivisions();
        CountrySubdivision country = new CountrySubdivision();
        country.countrySubdivisionCode("USA");
        List list = new ArrayList();
        list.add(country);
        mockedReturn.setCountrySubdivisions(list);

        given(locationsAtmsReferenceService.getCountrySubdivisions(Mockito.anyString())).willReturn(mockedReturn);

        mvc.perform(get("/locations/atms/country-subdivisions?country_code=USA")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.countrySubdivisions[0].countrySubdivisionCode").value("USA"));
    }

    @org.junit.Test
    public void testGetCountrySubdivisions_ErrorHandling() throws Exception {
        doThrow(new ApiException(400, "Something went wrong")).when(locationsAtmsReferenceService).getCountrySubdivisions(Mockito.any());
        mvc.perform(get("/locations/atms/country-subdivisions?country_code=USA")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Something went wrong"));
    }


    @org.junit.Test
    public void testGetAtms_successfulRequest() throws Exception {
        Atms mockedReturn = new Atms();
        mockedReturn.setLimit(1);

        AtmSearch atmSearch = new AtmSearch();
        atmSearch.setAddressLine1("1 Oak Street");
        atmSearch.setCity("OFallon");
        atmSearch.setCountrySubdivisionCode("MO");
        atmSearch.setCountryCode("USA");
        atmSearch.setPostalCode("63368");

        String requestJson = objectMapper.writeValueAsString(atmSearch);

        given(locationsAtmsReferenceService.getAtms(Mockito.any(AtmSearch.class),
                Mockito.anyInt(),Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).willReturn(mockedReturn);

        mvc.perform(post("/locations/atms/searches?limit=1&offset=6&distance=10&distanceUnit=MI")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.limit").value(1));
    }

    @org.junit.Test
    public void testGetAtmsSearch_ErrorHandling() throws Exception {

        AtmSearch atmSearch = new AtmSearch();
        atmSearch.setAddressLine1("1 Oak Street");

        String requestJson = objectMapper.writeValueAsString(atmSearch);
        doThrow(new ApiException(400, "Something went wrong")).when(locationsAtmsReferenceService).getAtms(Mockito.any(AtmSearch.class),
                Mockito.anyInt(),Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        mvc.perform(post("/locations/atms/searches?limit=1&offset=6&distance=10&distanceUnit=MI")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Something went wrong"));
    }

}
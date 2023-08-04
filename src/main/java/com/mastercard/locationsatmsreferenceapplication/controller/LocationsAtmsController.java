/*
 *  Copyright (c) 2023 Mastercard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mastercard.locationsatmsreferenceapplication.controller;

import com.mastercard.locationsatmsreferenceapplication.services.LocationsAtmsReferenceService;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.AtmSearch;
import org.openapitools.client.model.Atms;
import org.openapitools.client.model.Countries;
import org.openapitools.client.model.CountrySubdivisions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/locations/atms")
public class LocationsAtmsController {

    private static final Logger logger = LoggerFactory.getLogger(LocationsAtmsController.class);
    @Autowired
    LocationsAtmsReferenceService locationsAtmsReferenceService;


    @GetMapping(value = {"/countries"}, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Countries> getCountries(@RequestParam(required = false, name = "client_id") String clientId) {
        try {
            logger.info("Request received for countries search with the client_id : {}", clientId);
            return ResponseEntity.ok(locationsAtmsReferenceService.getCountries());
        } catch(ApiException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.valueOf(exception.getCode()));
        }
    }

    @GetMapping(value = {"/country-subdivisions"}, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<CountrySubdivisions> getCountrySubdivisions(@RequestParam(required = true, name = "country_code") String countryCode,
                                                                      @RequestParam(required = false, name = "client_id") String clientId) {
        try {
            logger.info("Request received - country-subdivisions -  for the countryCode {} ,  client_id : {}",countryCode, clientId);
            return ResponseEntity.ok(locationsAtmsReferenceService.getCountrySubdivisions(countryCode));
        } catch(ApiException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.valueOf(exception.getCode()));
        }
    }

    @PostMapping(value = {"/searches"}, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Atms> getAtms(@RequestParam(required = false, name = "limit") Integer limit,
                                        @RequestParam(required = false, name = "offset") Integer offset,
                                        @RequestParam(required = false, name = "distance") Integer distance,
                                        @RequestParam(required = false, name = "distanceUnit") String distanceUnit,
                                        @RequestParam(required = false, name = "addressLine1") String addressLine1,
                                        @RequestBody AtmSearch atmSearch) {
        try {
            logger.info("Request received for searches");
            return ResponseEntity.ok(locationsAtmsReferenceService.getAtms(atmSearch,limit,offset,distance,distanceUnit));
        } catch(ApiException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.valueOf(exception.getCode()));
        }
    }

}

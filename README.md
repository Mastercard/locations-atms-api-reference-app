# Locations Atms API Reference Application

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Mastercard/loyalty-user-management-reference/blob/master/LICENSE)

## Table of Contents

- [Overview](#overview)
    * [Compatibility](#compatibility)
    * [References](#references)
    * [Frameworks](#frameworks)
- [Setup](#setup)
    * [Prerequisites](#prerequisites)
    * [Application Configuration](#configuration)
    * [Build and Execute](#build-and-execute)
    * [Reference Application Usage](#reference-application-usage)
    * [Integrating with OpenAPI Generator](#integrating-with-openapi-generator)
- [Use Cases](#use-cases)
    * [Parameters](#parameters)
    * [Request Body](#requestbody)
    * [Countries](#countries)
    * [Country SubDivisions](#country-subdivisions)
    * [ATM Searches](#searches)
- [API Reference](#api-reference)
    - [Reference App API Reference](#ref-app-api-reference)
    - [Locations ATMs API Reference](#locations-atms-api-reference)
- [Authentication](#authentication)
    * [Mastercard's OAuth Library](#oauth-library)
- [Support](#support)
- [License](#license)
    

## Overview <a name="overview"></a>
This project showcases retrieving ATM locations using the [Locations Atms API](https://developer.mastercard.com/locations/documentation/).

This application illustrates connecting to the Locations Atms API using Mastercard's OAuth library, and an OpenAPI generated client.

### Compatibility <a name="compatibility"></a>
 * [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or later
 
### References <a name="references"></a>
* [Mastercard’s OAuth Signer library](https://github.com/Mastercard/oauth1-signer-java)
* [Using OAuth 1.0a to Access Mastercard APIs](https://developer.mastercard.com/platform/documentation/using-oauth-1a-to-access-mastercard-apis/)

## Frameworks <a name="frameworks"></a>
- [Spring](https://spring.io/projects/spring-boot)
- [OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator)
- [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Setup <a name="setup"></a>

### Prerequisites <a name="prerequisites"></a>

* [Mastercard Developers Account](https://developer.mastercard.com/dashboard)
* A text editor or IDE
* [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
* [Spring Boot 2.2+](https://spring.io/projects/spring-boot)
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi)
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.

### Application Configuration <a name="configuration"> </a>
1. Follow this [credentials quick guide](https://developer.mastercard.com/platform/documentation/getting-started-with-mastercard-apis/quick-start-guide/) to get the credentials needed to run this application
    - Be sure to add `Locations` to your project.
    - A zip file will be downloaded automatically with your key.
2. Take note of the given **consumer key**, **keyalias**, and **keystore password** given upon the project creation.
3. Extract the zip file which contains `.p12` file.
4. Update your credentials in the properties found in `/src/main/resources/application.properties`.

#### application.properties

`mastercard.api.base-path=https://sandbox.api.mastercard.com/locations/atms`, is the sandbox application URL to connect to Mastercard. 
 - **Note**: For production usage, use `https://api.mastercard.com/locations/atms`.
    
**Below properties will be required for authentication of API calls.**
    
* `mastercard.p12.path=`, this refers to .p12 file found in the signing key. Place the downloaded .p12 file path here. 
* `mastercard.consumer.key=`, this refers to your consumer key. Copy it from "Keys" section on your project page in [Mastercard Developers](https://developer.mastercard.com/dashboard). 
* `mastercard.keystore.alias=keyalias`, this is the default value of key alias. If it is modified, use the updated one from keys section in [Mastercard Developers](https://developer.mastercard.com/dashboard). 
* `mastercard.keystore.pass=keystorepassword`, this is the default value of key alias. If it is modified, use the updated one from keys section in [Mastercard Developers](https://developer.mastercard.com/dashboard). 

### Build and Execute <a name="build-and-execute"> </a>
1. Run `mvn clean install` from the root of the project directory.
    * When install is run, the [OpenAPI Generator plugin](#integrating-with-openapi-generator) will generate the sources for connecting to the Locations API.
2. run `java -jar target/locations-atms-reference-app-X.X.X.jar` to start the project.
    - **Notice**: Replace `X` with version of the reference app.
    - **Example**: `java -jar target/locations-atms-reference-app-1.0.0.jar`
3. Navigate to `http://localhost:8080/` in your browser.
    - **Example**: `http://localhost:8080//locations/atms/countries` a GET call to retrieve countries endpoint for ATM Locations country list.
    - **Example**: `http://localhost:8080/locations/atms/country-subdivisions?country_code=USA` a GET call to retrieve USA country sub divisions endpoint for ATM Locations country sub division list.
    
### Requesting a ATM Searches POST call<a name="searches-post-call"> </a>
1. A tool like Postman or Insomnia is needed to perform a POST call

2. select the option POST from the HTTP request dropdown.

3. Provide the URL in the address bar. 
    - **Example**: `http://localhost:8080/locations/atms/searches`

4. Select the BODY tab around the address bar and select the option type JSON.

5. An ATM location search can be performed either by longitude, latitude or by address and postal code like in the below example in BODY.
    - Example: 
    ```
                    {
                        "longitude":"38.6270",
                        "latitude":"-90.1994"
                    }
    ```
    - Example: 
    ```
                    {
                       "addressLine1":"1 CONVENTION CENTER PLZ",
                       "addressLine2":"",
                       "city":"SAINT CHARLES",
                       "countryCode":"USA",
                       "countrySubdivisionCode":"MO",
                       "postalCode":"63303"
                    }
    ```

### Reference Application Usage <a name="reference-application-usage"></a>
- Use the locations app in the sandbox to find atm searches.
- click on a marker to open more information on the right panel.
- click on right panel to expand the marker to make calls.

### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

See also:
* [OpenAPI Generator (maven Plugin)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [OpenAPI Generator (executable)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli)
* [CONFIG OPTIONS for java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

#### OpenAPI Generator Plugin Configuration
 
 ```xml
<plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>4.3.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/locations-atms-api-spec.yaml</inputSpec>
                            <generatorName>java</generatorName>
                            <library>okhttp-gson</library>
                            <configurationFile>${project.basedir}/src/main/resources/openapi-config.json</configurationFile>
                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <configOptions>
                                <sourceFolder>src/gen/java/main</sourceFolder>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
 </plugin>
 ```

## Use Cases <a name="use-cases"></a>

#### Parameters <a name="parameters"></a>
These are the parameters used for the Locations API. Few parameters are required.

| Name                      | Type      | Default Value      | Purpose       |
|---------------------------|-----------|--------------------|---------------|
| client_id                 | String    | null               | For client_id; client_id is used to identify the client performing the search.        |
| country_code              | String    |                    | For country subdivisions; country_code is used to identify the country to be searched. |
| offset                    | int       | 0                  | For Pagination; offset is used to offset the start of the list.        |
| limit                     | int       | 25                 | For Pagination; limit is used to limit the number of entities returned |
| distance                  | int       | 5                  | For ATMs search based on radius; distance is used to search the length of distance from the centroid point. |
| distanceUnit              | String    | MILE               | Unit of measurement for calculating the radius search; Measurement of distance unit in miles or kilometers. Value string (MILE or KM) |

#### BodyParameters <a name="bodyparams"></a>
These are the parameters used for the Locations API. Few parameters are required.

| Name                      | Type      | Default Value | Purpose       |
|---------------------------|-----------|---------------|---------------|
| addressLine1              | String    |               | For ATMs search; AddressLine1 is used to search the ATM locations based on the address|
| addressLine2	            | String    |               | For ATMs search; AddressLine2 is used to search the ATM locations based on the address |
| city                      | String    |               | For ATMs search; City is used to search the ATM locations based of address|
| countrySubdivisionCode    | String    |               | For ATMs search; City is used to search the ATM locations based of address |
| latitude                  | String    |               | For ATMs search ; latitude is used to perform atm search based on latitude and longitude|
| longitude                 | String    |               | For ATMs search ; latitude is used to perform atm search based on latitude and longitude |
| postalCode                | String    |               | For ATMs search; postal code is used to search ATM locations based on the address|



### Countries <a name="countries"></a>
> Get All Countries information. 

| Locations Atms API URL    | Method        | Parameters        | Request Model | Response model|
|---------------------------|---------------|-------------------|---------------|---------------|
| /countries                | GET           | client_id         |               | Countries     | 

### Country SubDivisions <a name="country-subdivisions"></a>
> Get Country Subdivisions based on country code. 

| Locations Atms API URL    | Method        | Parameters            | Request Model | Response model        |
|---------------------------|---------------|-----------------------|---------------|-----------------------|
| /country-subdivisions     | GET           |countryCode, client_id |               | CountrySubdivisions   |

### ATM Searches <a name="searches"></a>
> Retrieving ATM locations based on a latitude/longitude or on a physical address or postal code 

| Locations Atms API URL    | Method        | Parameters                        | BodyParameters                                                                        | Request Model   | Response model|
|---------------------------|---------------|-----------------------------------|---------------------------------------------------------------------------------------|-----------------|---------------|
| /searches                 | POST          | offset,limit,distance,distanceUnit| addressLine1,addressLine2,city,countrySubdivisionCode,latitude,longitude,postalcode   | AtmSearch       | Atms          |

## API Reference <a name="api-reference"></a>

### Reference Application API Reference <a name="ref-app-api-reference"></a>

| Reference App URL                         | Parameters                        | Reference App Usage             | Locations ATMs Endpoint Used  |
|-------------------------------------------|-----------------------------------|---------------------------------|-----------------------|
|**/locations/atms/countries**              | client_id                         | Lists countries where master card ATMs are present | /locations/atms/countries |
|**/locations/atms/country-subdivisions**   | country_code,client_id            | Lists country subdivision details of atms present by country code USA or CAN | /locations/atms/country-subdivisions |
|**/locations/atms/searches**               | offset,limit,distance,distanceUnit| Search for all Atms 5 *distanceUnits* around lat, long or address | /locations/atms/searches |
Example Search Request in a rest client (or browser) of your choice: `http://localhost:8080/locations/atms/searches?latitude=45.50977&longitude=-73.55163`

### Locations Atms API Reference <a name="locations-atms-api-reference"></a>

See the [API Reference](https://developer.mastercard.com/locations/documentation/api-reference/) page in the documentation. 

| API Endpoint                  | Description                                                       |
| ----------------------------- | ----------------------------------------------------------------- |
| [Get Countries](https://developer.mastercard.com/locations/documentation/api-reference#api)                 | Lists countries where master card ATMs are present |
| [Get Country Subdivisions Details](https://developer.mastercard.com/locations/documentation/api-reference#api)            | Lists country subdivision details of atms present by country code USA or CAN              |
| [ATM Search](https://developer.mastercard.com/locations/documentation/api-reference#api)   | Search for ATM locations around specific coordinates or using a address                      |

## Authentication <a name="authentication"></a>

### Mastercard oauth1 Signer Library <a name="oauth-library"></a>
This dependency is required to properly call the API.
```xml
<dependency>
    <groupId>com.mastercard.developer</groupId>
    <artifactId>oauth1-signer</artifactId>
    <version>1.2.3</version>
</dependency>
```
[Link to the oauth1 library's Github](https://github.com/Mastercard/oauth1-signer-java)

[Looking for other languages?](https://github.com/Mastercard?q=oauth&type=&language=)

See the code used in this application to utilize the library.
```Java
Found in /src/java/com.mastercard.locationsatmsreferenceapplication.config.ApiClientConfiguration

ApiClient client = new ApiClient();
HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger::info);
loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
try {
    client.setBasePath(basePath);
    client.setHttpClient(
            client.getHttpClient()
                    .newBuilder()
                    .addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, getSigningKey()))
                    .addInterceptor(loggingInterceptor)
                    .build()
    );

    return client;
} catch (Exception e) {
    logger.error("Error occurred while configuring ApiClient", e);
}
return client;
```

## Support <a name="support"></a>
If you would like further information, please send an email to apisupport@mastercard.com

## License <a name="license"></a>
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

**Copyright © 1994-2023, All Rights Reserved by Mastercard.**

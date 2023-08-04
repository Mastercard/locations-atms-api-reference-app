package com.mastercard.locationsatmsreferenceapplication.exception;

import org.junit.jupiter.api.Test;

public class LocationsAtmsServiceExceptionTest {

    private static final String MESSAGE = "Something went wrong";

    @Test
    void testServiceExceptionMessage() {
        Exception exception = new Exception(MESSAGE);
        LocationsAtmsServiceException locationsAtmsServiceException = new LocationsAtmsServiceException(exception);
        assert(locationsAtmsServiceException.getMessage().contains(MESSAGE));
        }}
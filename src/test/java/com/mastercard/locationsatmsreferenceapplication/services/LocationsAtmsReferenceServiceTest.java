package com.mastercard.locationsatmsreferenceapplication.services;

import com.mastercard.locationsatmsreferenceapplication.exception.LocationsAtmsServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.openapitools.client.ApiException;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class LocationsAtmsReferenceServiceTest {
    @InjectMocks
    private DefaultLocationsAtmsReferenceService classUnderTest;

    @Test
    public void testGetErrorAttributes_handleLocationsAtmServiceException() {
        String result = classUnderTest.getErrorAttributes(new LocationsAtmsServiceException(new Exception("Something went wrong")));

        assert (result.contains("Something went wrong"));
    }

    @Test
    public void testGetErrorAttributes_handleApiExceptionWithJsonBody() {
        ApiException thrownException = new ApiException(400, new HashMap<>(), getErrorResponseBody("INVALID_REQUEST_PARAMETER", "Offset index must not be less than zero", true));
        String result = classUnderTest.getErrorAttributes(thrownException);
        assert (result.contains("INVALID_REQUEST_PARAMETER"));
    }

    @Test
    public void testGetErrorAttributes_handleApiExceptionWithoutJsonBody() {
        ApiException thrownException = new ApiException(400, new HashMap<>(), "Something went wrong");
        String result = classUnderTest.getErrorAttributes(thrownException);
        assert (result.contains("Something went wrong"));
    }

    public static String getErrorResponseBody(String reasonCode, String description, boolean recoverable) {
        return "{\n" +
                "  \"Errors\": {\n" +
                "    \"Error\": [\n" +
                "      {\n" +
                "        \"Source\": \"locations-atms-api\",\n" +
                "        \"ReasonCode\": \"" + reasonCode + "\",\n" +
                "        \"Description\": \"" + description + "\",\n" +
                "        \"Recoverable\":" + recoverable + ",\n" +
                "        \"Details\": null\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
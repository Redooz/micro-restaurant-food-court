package com.pragma.microservicefoodcourt.configuration.client;

import com.pragma.microservicefoodcourt.domain.exception.RestaurantOwnerNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class UserErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new RestaurantOwnerNotFoundException();
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}

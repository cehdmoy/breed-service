package cehd.breeds.api.gateway;

import org.springframework.web.client.HttpClientErrorException;

public class GatewayNotFoundException extends RuntimeException {
    public GatewayNotFoundException(HttpClientErrorException e) {
        super(e);
    }
}

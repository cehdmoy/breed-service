package cehd.breeds.api.gateway.images;

import cehd.breeds.api.gateway.GatewayNotFoundException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

public class ImagesGatewayImp implements ImagesGateway {
    private final String urlBase;
    private final RestOperations restOperations;

    public ImagesGatewayImp(String urlBase, RestOperations restOperations) {
        this.urlBase = urlBase;
        this.restOperations = restOperations;
    }

    public ExternalImagesDto retrieveImagesFor(String name) {
        String urlRequest = urlBase + buildPath(name);
        try {
            return restOperations.getForObject(urlRequest, ExternalImagesDto.class);
        } catch (HttpClientErrorException e) {
            return handleException(e);
        }
    }

    private ExternalImagesDto handleException(HttpClientErrorException e) {
        if (e.getRawStatusCode() == 404) {
            throw new GatewayNotFoundException(e);
        } else throw e;
    }

    private String buildPath(String name) {
        return "breed/{BREED_REPLACE_TOKEN}/images".replace("{BREED_REPLACE_TOKEN}", name);
    }
}

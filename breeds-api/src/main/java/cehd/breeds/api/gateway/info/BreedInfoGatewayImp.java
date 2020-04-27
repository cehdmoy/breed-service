package cehd.breeds.api.gateway.info;

import org.springframework.web.client.RestOperations;

public class BreedInfoGatewayImp implements BreedInfoGateway{

    private final String urlBase;

    private final RestOperations restOperations;

    public BreedInfoGatewayImp(String urlBase, RestOperations restOperations) {
        this.urlBase = urlBase;
        this.restOperations = restOperations;
    }

    public ExternalBreedInfoDto retrieveBreedInfo() {
        String url = urlBase + "breeds/list/all";
        return restOperations.getForObject(url, ExternalBreedInfoDto.class);
    }
}

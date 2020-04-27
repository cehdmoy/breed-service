package cehd.breeds.api.facotory;

import cehd.breeds.api.gateway.images.ImagesGateway;
import cehd.breeds.api.gateway.images.ImagesGatewayImp;
import cehd.breeds.api.gateway.info.BreedInfoGateway;
import cehd.breeds.api.gateway.info.BreedInfoGatewayImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("!dev")
public class FactoryDefaultProfile {
    @Value("${api.breed.config.service.url}")
    private String url;

    @Bean
    public ImagesGateway createImagesGateway() {
        return new ImagesGatewayImp(getUrlBase(), new RestTemplate());
    }

    @Bean
    public BreedInfoGateway createBreedInfoGateway() {
        return new BreedInfoGatewayImp(getUrlBase(), new RestTemplate());
    }

    private String getUrlBase() {
        final RestTemplate restTemplate = new RestTemplate();
        url = "http://localhost:8082/config";
        return restTemplate.getForObject(url, String.class);
    }

}

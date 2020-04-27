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
@Profile("dev")
public class FactoryDevProfile {

    @Value("${api.breed.url}")
    private String urlBase;

    @Bean
    public ImagesGateway createImagesGateway() {
        return new ImagesGatewayImp(urlBase, new RestTemplate());
    }

    @Bean
    public BreedInfoGateway createBreedInfoGateway() {
        return new BreedInfoGatewayImp(urlBase, new RestTemplate());
    }
}

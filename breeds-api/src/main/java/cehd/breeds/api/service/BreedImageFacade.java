package cehd.breeds.api.service;

import cehd.breeds.api.dto.Image;
import cehd.breeds.api.gateway.images.ExternalImagesDto;
import cehd.breeds.api.gateway.images.ImagesGateway;
import cehd.breeds.api.mapper.BreedMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BreedImageFacade {

    private final BreedMapper breedMapper;

    private final ImagesGateway imagesGateway;

    public BreedImageFacade(BreedMapper breedMapper, ImagesGateway imagesGateway) {
        this.breedMapper = breedMapper;
        this.imagesGateway = imagesGateway;
    }

    public List<Image> makeImageInfo(String breedName) {
        final ExternalImagesDto externalImagesDto = imagesGateway.retrieveImagesFor(breedName);
        return breedMapper.buildImages(externalImagesDto);
    }
}

package cehd.breeds.api.service;

import cehd.breeds.api.dto.BreedResponseDto;
import cehd.breeds.api.dto.Image;
import cehd.breeds.api.logic.contract.BreedInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedService {

    private final BreedImageFacade breedImageFacade;

    private final BreedInfoFacade breedInfoFacade;

    public BreedService(BreedImageFacade breedImageFacade, BreedInfoFacade breedInfoFacade) {
        this.breedImageFacade = breedImageFacade;
        this.breedInfoFacade = breedInfoFacade;
    }

    public BreedResponseDto buildBreedComposeResponseBy(String breedName) {
        final BreedInfo breedInfo = breedInfoFacade.makeBreedInfo(breedName);
        final List<Image> images = breedImageFacade.makeImageInfo(breedName);
        return buildResponse(breedInfo, images);
    }

    private BreedResponseDto buildResponse(BreedInfo breedInfo, List<Image> images) {
        return BreedResponseDto.
                builder()
                .breed(breedInfo.getName())
                .subBreeds(breedInfo.getSubBreed())
                .images(images)
                .build();
    }

}

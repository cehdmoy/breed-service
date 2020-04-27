package cehd.breeds.api.service;

import cehd.breeds.api.dto.Image;
import cehd.breeds.api.gateway.GatewayNotFoundException;
import cehd.breeds.api.gateway.images.ExternalImagesDto;
import cehd.breeds.api.gateway.images.ImagesGateway;
import cehd.breeds.api.mapper.BreedMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cehd.breeds.api.helper.POJOExamplesHelper.prepareMockExternalImagesDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BreedImageFacadeTest {

    private BreedMapper breedMapper = mock(BreedMapper.class);

    private ImagesGateway imagesGateway = mock(ImagesGateway.class);

    private BreedImageFacade breedImageFacade;

    @BeforeEach
    void setUp() {
        breedImageFacade = new BreedImageFacade(breedMapper, imagesGateway);
    }

    @Test
    void isClassThere() {
        assertNotNull(breedImageFacade);
    }

    @Test
    void checkMakeImagesInfo() throws JsonProcessingException {
        when(breedMapper.buildImages(any())).thenCallRealMethod();
        when(imagesGateway.retrieveImagesFor(any())).thenReturn(prepareMockExternalImagesDto());
        String breedName = "bulldog";

        final List<Image> images = breedImageFacade.makeImageInfo(breedName);
        assertNotNull(images);
        assertEquals(150, images.size());
        checkElementByByOne(prepareMockExternalImagesDto(), images);
    }

    @Test
    void exceptionFromGatewayIsNotHalerHere() throws JsonProcessingException {
        when(breedMapper.buildImages(any())).thenThrow(new GatewayNotFoundException(null));
        when(imagesGateway.retrieveImagesFor(any())).thenReturn(prepareMockExternalImagesDto());
        String breedName = "bulldog";
        assertThrows(GatewayNotFoundException.class, () -> breedImageFacade.makeImageInfo(breedName));
    }


    private void checkElementByByOne(ExternalImagesDto externalImagesDto, List<Image> images) {
        externalImagesDto
                .getMessage()
                .forEach(e -> assertTrueAbstraction(images, e));
    }

    private void assertTrueAbstraction(List<Image> images, String e) {
        assertTrue(images.stream().anyMatch(l -> l.getUrl().equals(e)));
    }
}

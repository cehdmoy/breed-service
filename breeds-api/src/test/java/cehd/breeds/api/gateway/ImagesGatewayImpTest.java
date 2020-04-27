package cehd.breeds.api.gateway;

import cehd.breeds.api.gateway.images.ExternalImagesDto;
import cehd.breeds.api.gateway.images.ImagesGatewayImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import static cehd.breeds.api.helper.POJOExamplesHelper.prepareMockExternalImagesDto;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImagesGatewayImpTest {


    private String urlBase = "https://dog.ceo/api/";

    private RestOperations restOperations = mock(RestOperations.class);

    private ImagesGatewayImp imagesGatewayImp;

    @BeforeEach
    void setUp() {
        imagesGatewayImp = new ImagesGatewayImp(urlBase, restOperations);
    }

    @Test
    void isClassThere() {
        assertNotNull(imagesGatewayImp);
    }

    @Test
    void retrieveBreedImage() throws JsonProcessingException {
        ExternalImagesDto externalBreedInfoDtoMock = prepareMockExternalImagesDto();
        when(restOperations.getForObject(anyString(), any())).thenReturn(externalBreedInfoDtoMock);
        String name = "bulldog";
        ExternalImagesDto externalBreedInfoDto = imagesGatewayImp.retrieveImagesFor(name);
        assertNotNull(externalBreedInfoDto);
    }


    @Test
    void retrieveForOForBreedImage() {
        String name = "bulldognñlasdjflñajgdfñl";
        when(restOperations.getForObject(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, name + "not there"));
       assertThrows(GatewayNotFoundException.class,()-> imagesGatewayImp.retrieveImagesFor(name));
    }

    @Test
    void retrieveErrorBreedImage() {
        String name = "bulldognñlasdjflñajgdfñl";
        when(restOperations.getForObject(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_GATEWAY, name + "not there"));
        assertThrows(HttpClientErrorException.class,()-> imagesGatewayImp.retrieveImagesFor(name));
    }


}

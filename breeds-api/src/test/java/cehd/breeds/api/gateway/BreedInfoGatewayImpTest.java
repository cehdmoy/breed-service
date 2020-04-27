package cehd.breeds.api.gateway;

import cehd.breeds.api.gateway.info.BreedInfoGatewayImp;
import cehd.breeds.api.gateway.info.ExternalBreedInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestOperations;

import static cehd.breeds.api.helper.POJOExamplesHelper.prepareMockExternalBreedInfoDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BreedInfoGatewayImpTest {


    private String urlBase = "https://dog.ceo/api/";

    private RestOperations restOperations = mock(RestOperations.class);

    private BreedInfoGatewayImp breedInfoGatewayImp;

    @BeforeEach
    void setUp() {
        breedInfoGatewayImp = new BreedInfoGatewayImp(urlBase, restOperations);
    }

    @Test
    void isClassThere() {
        assertNotNull(breedInfoGatewayImp);
    }

    @Test
    void retrieveBreedInfo() throws JsonProcessingException {
        ExternalBreedInfoDto externalBreedInfoDtoMock = prepareMockExternalBreedInfoDto();
        when(restOperations.getForObject(anyString(), any())).thenReturn(externalBreedInfoDtoMock);
        ExternalBreedInfoDto externalBreedInfoDto = breedInfoGatewayImp.retrieveBreedInfo();
        checkResponse(externalBreedInfoDto);
    }

    private void checkResponse(ExternalBreedInfoDto externalBreedInfoDto) {
        assertNotNull(externalBreedInfoDto);
        assertNotNull(externalBreedInfoDto.getMessage());
        assertNotNull(externalBreedInfoDto.getStatus());
        assertEquals(94, externalBreedInfoDto.getMessage().size());
    }


}

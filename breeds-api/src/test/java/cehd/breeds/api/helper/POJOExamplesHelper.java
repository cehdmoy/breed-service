package cehd.breeds.api.helper;

import cehd.breeds.api.gateway.images.ExternalImagesDto;
import cehd.breeds.api.gateway.info.ExternalBreedInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJOExamplesHelper {

    private static final  ReaderFileHelper readerFileHelper = new ReaderFileHelper();


    public static ExternalBreedInfoDto prepareMockExternalBreedInfoDto() throws JsonProcessingException {
        String jsonMock = readerFileHelper.readFile("breed_list.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonMock, ExternalBreedInfoDto.class);
    }

    public static ExternalImagesDto prepareMockExternalImagesDto() throws JsonProcessingException {
        String jsonMock = readerFileHelper.readFile("breed_images.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonMock, ExternalImagesDto.class);
    }
}

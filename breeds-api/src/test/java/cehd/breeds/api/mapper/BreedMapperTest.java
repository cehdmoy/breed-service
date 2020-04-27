package cehd.breeds.api.mapper;

import cehd.breeds.api.dto.Image;
import cehd.breeds.api.gateway.images.ExternalImagesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cehd.breeds.api.helper.POJOExamplesHelper.prepareMockExternalImagesDto;
import static org.junit.jupiter.api.Assertions.*;

public class BreedMapperTest {

    private BreedMapper breedMapper;

    @BeforeEach
    void setUp() {
        breedMapper = new BreedMapper();
    }

    @Test
    void isClassThere() {
        assertNotNull(breedMapper);
    }

    @Test
    void checkCreateImagesFromRawData() throws JsonProcessingException {
        final ExternalImagesDto externalImagesDto = prepareMockExternalImagesDto();
        final List<Image> images = breedMapper.buildImages(externalImagesDto);
        assertNotNull(images);
        assertEquals(150, images.size());
        checkElementByByOne(externalImagesDto, images);
    }

    private void checkElementByByOne(ExternalImagesDto externalImagesDto, List<Image> images) {
        externalImagesDto
                .getMessage()
                .forEach(e-> assertTrueAbstraction(images, e));
    }

    private void assertTrueAbstraction(List<Image> images, String e) {
        assertTrue( images.stream().anyMatch(l->l.getUrl().equals(e)));
    }

}

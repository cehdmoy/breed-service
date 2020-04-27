package cehd.breeds.api.service;

import cehd.breeds.api.dto.BreedResponseDto;
import cehd.breeds.api.dto.Image;
import cehd.breeds.api.logic.contract.BreedInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BreedServiceTest {

    public static final String BULLDOG = "bulldog";
    private BreedImageFacade breedImageFacade = mock(BreedImageFacade.class);
    private BreedInfoFacade breedInfoFacade = mock(BreedInfoFacade.class);
    private BreedService breedService;

    @BeforeEach
    void setUp() {
        breedService = new BreedService(breedImageFacade, breedInfoFacade);
    }

    @Test
    void isClassThere() {
        assertNotNull(breedService);
    }

    @Test
    void givenBulldogThatIsInBothGatewaysIsAbleToBuildWholeResponse() {
        when(breedImageFacade.makeImageInfo(BULLDOG)).thenReturn(listResponse());
        when(breedInfoFacade.makeBreedInfo(BULLDOG)).thenReturn(buildBreedInfo());
        final BreedResponseDto breedResponseDto = breedService.buildBreedComposeResponseBy(BULLDOG);
        assertNotNull(breedResponseDto);
        assertEquals(BULLDOG, breedResponseDto.getBreed());
        assertEquals(2, breedResponseDto.getSubBreeds().size());
        assertTrue(breedResponseDto.getSubBreeds().stream().anyMatch("american"::equals));
        assertTrue(breedResponseDto.getSubBreeds().stream().anyMatch("british"::equals));
        assertEquals(2, breedResponseDto.getImages().size());
        assertTrue(breedResponseDto.getImages().stream().anyMatch(e -> e.getUrl().equals("url1")));
        assertTrue(breedResponseDto.getImages().stream().anyMatch(e -> e.getUrl().equals("url2")));
    }

    @Test
    void checkDependencyUse() {
        when(breedImageFacade.makeImageInfo(BULLDOG)).thenReturn(listResponse());
        when(breedInfoFacade.makeBreedInfo(BULLDOG)).thenReturn(buildBreedInfo());
        breedService.buildBreedComposeResponseBy(BULLDOG);
        verify(breedInfoFacade).makeBreedInfo(BULLDOG);
        verify(breedImageFacade).makeImageInfo(BULLDOG);
    }

    private BreedInfo buildBreedInfo() {
        final List<String> subBreed = List.of("american", "british");
        return BreedInfo.builder().name(BULLDOG).subBreed(subBreed).build();
    }

    private List<Image> listResponse() {
        final Image image1 = Image.builder().url("url1").build();
        final Image image2 = Image.builder().url("url2").build();
        return List.of(image1, image2);
    }
}

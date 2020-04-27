package cehd.breeds.api.service;

import cehd.breeds.api.gateway.info.BreedInfoGateway;
import cehd.breeds.api.logic.BreedListsHandlerImp;
import cehd.breeds.api.logic.contract.BreedNotFoundException;
import cehd.breeds.api.logic.contract.BreedInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cehd.breeds.api.helper.POJOExamplesHelper.prepareMockExternalBreedInfoDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BreedInfoFacadeTest {

    public static final String BULLDOG = "bulldog";

    private BreedInfoGateway breedInfoGateway = mock(BreedInfoGateway.class);

    private BreedListsHandlerImp breedListsHandler = mock(BreedListsHandlerImp.class);

    private BreedInfoFacade breedInfoFacade;

    @BeforeEach
    void setUp() {
        breedInfoFacade = new BreedInfoFacade(breedInfoGateway, breedListsHandler);
    }

    @Test
    void isClassThere() {
        assertNotNull(breedInfoFacade);
    }

    @Test
    void givenAValidListOFBreedAndABulldogNameMakeBreedInfoIsAbleToBuildIt() throws JsonProcessingException {
        when(breedInfoGateway.retrieveBreedInfo()).thenReturn(prepareMockExternalBreedInfoDto());
        when(breedListsHandler.retrieveBreedInfoFrom(any(), anyString())).thenCallRealMethod();
        final BreedInfo bulldog = breedInfoFacade.makeBreedInfo(BULLDOG);
        assertNotNull(bulldog);
        assertEquals(BULLDOG, bulldog.getName());
        assertTrue(bulldog.getSubBreed().stream().anyMatch(e -> e.equals("boston")));
        assertTrue(bulldog.getSubBreed().stream().anyMatch(e -> e.equals("english")));
        assertTrue(bulldog.getSubBreed().stream().anyMatch(e -> e.equals("french")));
    }

    @Test
    void givenAValidBreedNameDependencyUsedAreBeenVerified() throws JsonProcessingException {
        when(breedInfoGateway.retrieveBreedInfo()).thenReturn(prepareMockExternalBreedInfoDto());
        when(breedListsHandler.retrieveBreedInfoFrom(any(), anyString())).thenCallRealMethod();
        breedInfoFacade.makeBreedInfo(BULLDOG);
        verify(breedInfoGateway).retrieveBreedInfo();
        verify(breedListsHandler).retrieveBreedInfoFrom(any(), anyString());
    }

    @Test
    void givenANonValidBreedNameThatIsNotInAllDataDependencyUsedAreBeenVerified() throws JsonProcessingException {
        when(breedInfoGateway.retrieveBreedInfo()).thenReturn(prepareMockExternalBreedInfoDto());
        when(breedListsHandler.retrieveBreedInfoFrom(any(), anyString())).thenCallRealMethod();
        assertThrows(BreedNotFoundException.class, () -> breedInfoFacade.makeBreedInfo("GUACHIMINGO"));
        verify(breedInfoGateway).retrieveBreedInfo();
        verify(breedListsHandler).retrieveBreedInfoFrom(any(), anyString());
    }

}

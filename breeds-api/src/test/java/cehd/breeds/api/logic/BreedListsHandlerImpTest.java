package cehd.breeds.api.logic;

import cehd.breeds.api.logic.contract.BreedInfo;
import cehd.breeds.api.logic.contract.BreedNotFoundException;
import cehd.breeds.api.logic.contract.RawBreedInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BreedListsHandlerImpTest {

    private BreedListsHandlerImp breedListsHandlerImp;

    @BeforeEach
    void setUp() {
        breedListsHandlerImp = new BreedListsHandlerImp();
    }

    @Test
    void isClassThere() {
        assertNotNull(breedListsHandlerImp);
    }


    @Test
    void givenAABulldogIntoInputValidDadaWhenExecuteRetrieveInfoIsAbleToDoItRetrievingSubBreedList() {
        String breedName = "bulldog";
        RawBreedInfo rawBreedInfo = RawBreedInfo.builder()
                .rawData(buildRawBreedData())
                .build();
        BreedInfo response = breedListsHandlerImp.retrieveBreedInfoFrom(rawBreedInfo, breedName);
        assertNotNull(response);
        assertEquals(breedName, response.getName());
        assertTrue(response.getSubBreed().stream().anyMatch(l -> l.equals("boston")));
        assertTrue(response.getSubBreed().stream().anyMatch(l -> l.equals("english")));
        assertTrue(response.getSubBreed().stream().anyMatch(l -> l.equals("french")));

    }

    @Test
    void givenAAdenineIntoInputValidDadaWhenExecuteRetrieveInfoIsAbleToDoItHandlingEmptyList() {
        String breedName = "affenpinscher";
        RawBreedInfo rawBreedInfo = RawBreedInfo.builder()
                .rawData(buildRawBreedData())
                .build();
        BreedInfo response = breedListsHandlerImp.retrieveBreedInfoFrom(rawBreedInfo, breedName);
        assertNotNull(response);
        assertEquals(breedName, response.getName());
        assertEquals(0, response.getSubBreed().size());
    }

    @Test
    void givenGalgoIsNotIntoInputValidDAtaWhenExecuteRetrieveInfoThrowBreedNotFoundException() {
        String breedName = "galgo";
        RawBreedInfo rawBreedInfo = RawBreedInfo.builder()
                .rawData(buildRawBreedData())
                .build();
        assertThrows(BreedNotFoundException.class, () -> breedListsHandlerImp.retrieveBreedInfoFrom(rawBreedInfo, breedName));
    }


    private Map<String, List<String>> buildRawBreedData() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("bulldog", List.of("boston", "english", "french"));
        data.put("affenpinscher", new ArrayList<>());
        return data;
    }
}

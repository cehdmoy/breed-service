package cehd.breeds.api.logic;

import cehd.breeds.api.logic.contract.BreedInfo;
import cehd.breeds.api.logic.contract.BreedListsHandler;
import cehd.breeds.api.logic.contract.BreedNotFoundException;
import cehd.breeds.api.logic.contract.RawBreedInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class BreedListsHandlerImp implements BreedListsHandler {

    public BreedInfo retrieveBreedInfoFrom(RawBreedInfo rawBreedInfo, String breedName) {

        Map<String, List<String>> rawData = rawBreedInfo.getRawData();

        Predicate<Map.Entry<String, List<String>>> byBreedName = e -> e.getKey().equals(breedName);

        return rawData
                .entrySet()
                .stream()
                .filter(byBreedName)
                .map(buildBreedInfo())
                .findFirst()
                .orElseThrow(() -> new BreedNotFoundException("Breed: " + breedName + " not found"));
    }


    private Function<Map.Entry<String, List<String>>, BreedInfo> buildBreedInfo() {
        return r -> BreedInfo
                .builder()
                .name(r.getKey())
                .subBreed(r.getValue())
                .build();
    }
}

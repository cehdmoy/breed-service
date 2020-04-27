package cehd.breeds.api.logic.contract;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
public class RawBreedInfo {

    private final Map<String, List<String>> rawData;

}

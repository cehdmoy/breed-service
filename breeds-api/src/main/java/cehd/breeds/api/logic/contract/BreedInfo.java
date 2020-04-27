package cehd.breeds.api.logic.contract;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BreedInfo {
    private final String name;
    private final List<String> subBreed;
}

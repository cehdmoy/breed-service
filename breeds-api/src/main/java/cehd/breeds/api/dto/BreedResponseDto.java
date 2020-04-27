package cehd.breeds.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BreedResponseDto {
    private String breed;
    private List<String> subBreeds;
    private List<Image> images;
}

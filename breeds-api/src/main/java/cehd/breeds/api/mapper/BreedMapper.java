package cehd.breeds.api.mapper;

import cehd.breeds.api.dto.Image;
import cehd.breeds.api.gateway.images.ExternalImagesDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public class BreedMapper {

    public List<Image> buildImages(ExternalImagesDto externalImagesDto) {
        final Function<String, Image> fromExternalRawImageToImage = rawImage -> Image.builder().url(rawImage).build();
        return externalImagesDto
                .getMessage()
                .stream()
                .map(fromExternalRawImageToImage)
                .collect(Collectors.toList());
    }
}

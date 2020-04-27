package cehd.breeds.api.controller;

import cehd.breeds.api.dto.BreedResponseDto;
import cehd.breeds.api.service.BreedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BreedController {

    private BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/breed/{name}")
    public ResponseEntity<BreedResponseDto> retrievePersonData(@PathVariable("name") String breadName) {
        final BreedResponseDto breedResponseDto = breedService.buildBreedComposeResponseBy(breadName);
        return ResponseEntity.ok(breedResponseDto);
    }

}

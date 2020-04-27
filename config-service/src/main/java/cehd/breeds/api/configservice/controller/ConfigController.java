package cehd.breeds.api.configservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${api.breed.url}")
    private String urlBase;

    @GetMapping("/config")
    public ResponseEntity<String> retrieveConf() {
        return ResponseEntity.ok(urlBase);
    }

}

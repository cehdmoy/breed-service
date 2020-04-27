package cehd.breeds.api.controller;

import cehd.breeds.api.logic.contract.BreedNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BreedNotFoundException.class)
    protected ResponseEntity<String> handheldBreedNotFoundException(BreedNotFoundException ex) {
       return ResponseEntity.status(404).body(ex.getMessage());
    }
}

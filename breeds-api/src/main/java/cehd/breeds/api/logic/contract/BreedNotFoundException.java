package cehd.breeds.api.logic.contract;

import java.util.function.Supplier;

public class BreedNotFoundException extends RuntimeException {
    public BreedNotFoundException(String s) {
        super(s);
    }
}

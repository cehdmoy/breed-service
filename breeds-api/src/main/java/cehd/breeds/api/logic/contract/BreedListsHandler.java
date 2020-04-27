package cehd.breeds.api.logic.contract;


public interface BreedListsHandler  {
    BreedInfo retrieveBreedInfoFrom(RawBreedInfo rawBreedInfo, String breedName);

}

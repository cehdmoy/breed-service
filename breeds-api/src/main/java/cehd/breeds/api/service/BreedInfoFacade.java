package cehd.breeds.api.service;

import cehd.breeds.api.gateway.info.BreedInfoGateway;
import cehd.breeds.api.gateway.info.ExternalBreedInfoDto;
import cehd.breeds.api.logic.contract.BreedInfo;
import cehd.breeds.api.logic.contract.BreedListsHandler;
import cehd.breeds.api.logic.contract.RawBreedInfo;
import org.springframework.stereotype.Component;

@Component
public class BreedInfoFacade {
    private final BreedInfoGateway breedInfoGateway;

    private final BreedListsHandler breedListsHandler;

    public BreedInfoFacade(BreedInfoGateway breedInfoGateway, BreedListsHandler breedListsHandler) {
        this.breedInfoGateway = breedInfoGateway;
        this.breedListsHandler = breedListsHandler;
    }

    public BreedInfo makeBreedInfo(String breedName) {
        final ExternalBreedInfoDto externalBreedInfo = breedInfoGateway.retrieveBreedInfo();
        final RawBreedInfo rawBreedInfo = RawBreedInfo
                .builder()
                .rawData(externalBreedInfo.getMessage())
                .build();
        return breedListsHandler.retrieveBreedInfoFrom(rawBreedInfo, breedName);
    }
}

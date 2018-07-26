package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.LocationUsecase;
import javax.inject.Inject;

public class LocationInteractor extends BaseInteractor {
    private final LocationUsecase locationUsecase;

    @Inject
    LocationInteractor(LocationUsecase locationUsecase) {
        this.locationUsecase = locationUsecase;
    }
}

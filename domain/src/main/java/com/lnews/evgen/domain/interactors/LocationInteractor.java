package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.entities.Location;
import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.AddressUseCase;
import com.lnews.evgen.domain.usecases.LastLocationUsecase;
import io.reactivex.Observable;
import io.reactivex.Single;
import javax.inject.Inject;

public class LocationInteractor extends BaseInteractor {
    private final LastLocationUsecase lastLocationUsecase;
    private final AddressUseCase addressUseCase;

    @Inject
    LocationInteractor(LastLocationUsecase lastLocationUsecase, AddressUseCase addressUseCase) {
        this.lastLocationUsecase = lastLocationUsecase;
        this.addressUseCase = addressUseCase;
    }

    public Single getLastLocation(){
        return lastLocationUsecase.execute();
    }

    public Observable getAddressFromLocation(Location location){
        return addressUseCase.execute(location);
    }
}

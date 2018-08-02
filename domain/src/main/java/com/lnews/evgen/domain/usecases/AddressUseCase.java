package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.Location;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Observable;
import javax.inject.Inject;

public class AddressUseCase {
    private final IRepository repository;

    @Inject
    AddressUseCase(IRepository repository){
        this.repository = repository;
    }

    public Observable execute(Location location){
        return repository.getAddressFromLocation(location);
    }
}

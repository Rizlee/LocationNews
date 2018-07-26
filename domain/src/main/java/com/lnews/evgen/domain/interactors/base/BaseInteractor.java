package com.lnews.evgen.domain.interactors.base;

import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import com.lnews.evgen.domain.usecases.base.FlowableUseCase;
import com.lnews.evgen.domain.usecases.base.ObservableUseCase;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.ResourceSubscriber;

public abstract class BaseInteractor {
    private final CompositeDisposable disposables;

    protected BaseInteractor() {
        disposables = new CompositeDisposable();
    }

    protected <InParam, OutParam> void execute(ObservableUseCase<InParam, OutParam> useCase, InParam inParam,
        DisposableObserver<OutParam> observer) {
        disposables.add(useCase.execute(inParam).subscribeWith(observer));
    }

    protected <InParam> void execute(CompletableUseCase<InParam> useCase, InParam inParam,
        DisposableCompletableObserver observer) {

        disposables.add(useCase.execute(inParam).subscribeWith(observer));
    }

    protected <InParam, OutParam> void execute(SingleUseCase<InParam, OutParam> useCase, InParam inParam,
        DisposableSingleObserver<OutParam> observer) {
        disposables.add(useCase.execute(inParam).subscribeWith(observer));
    }

    protected <InParam, OutParam> void execute(FlowableUseCase<InParam, OutParam> useCase, InParam inParam,
        ResourceSubscriber<OutParam> subscriber) {
        disposables.add(useCase.execute(inParam).subscribeWith(subscriber));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
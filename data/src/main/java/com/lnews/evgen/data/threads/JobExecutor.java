package com.lnews.evgen.data.threads;

import com.lnews.evgen.domain.executor.ExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JobExecutor implements ExecutionThread {

    @Inject
    JobExecutor() {}

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}

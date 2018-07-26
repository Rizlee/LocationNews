package com.lnews.evgen.domain.executor;

import io.reactivex.Scheduler;

public interface ExecutionThread {
    Scheduler getScheduler();
}

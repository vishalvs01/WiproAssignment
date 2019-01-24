package com.wiproassignment.common.schedulerprovider;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestingSchedulerProvider implements BaseSchedulerProvider {

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }
}

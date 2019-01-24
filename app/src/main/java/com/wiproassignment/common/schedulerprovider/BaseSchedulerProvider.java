package com.wiproassignment.common.schedulerprovider;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler io();

    Scheduler mainThread();
}

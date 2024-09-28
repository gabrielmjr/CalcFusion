package com.mjrfusion.app.calcfusion.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadUtils {
    private static final int NUMBER_OF_THREAD_POLLS = 3;
    public static final Executor threadPools = Executors.newFixedThreadPool(NUMBER_OF_THREAD_POLLS);
}

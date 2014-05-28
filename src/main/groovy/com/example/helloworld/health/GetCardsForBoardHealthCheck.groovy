package com.example.helloworld.health

import com.example.helloworld.core.Template
import com.google.common.base.Optional
import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result

public class GetCardsForBoardHealthCheck extends HealthCheck {

    protected GetCardsForBoardHealthCheck() {
        super("GetCardsForBoardHealthCheck")
    }

    @Override
    protected Result check() throws Exception {
//        return Result.healthy()
        return Result.unhealthy("Returning unhealthy result to turn healthcheck monitor red to prove healthcheck is being run")
    }
}

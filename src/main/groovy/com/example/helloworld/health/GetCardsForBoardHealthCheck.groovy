package com.example.helloworld.health

import com.example.helloworld.HelloWorldConfiguration
import com.example.helloworld.core.Template
import com.google.common.base.Optional
import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result
import groovyx.net.http.HttpResponseException
import junit.framework.AssertionFailedError

import static org.hamcrest.CoreMatchers.equalTo
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals

import groovyx.net.http.RESTClient

import static org.junit.Assert.assertThat


public class GetCardsForBoardHealthCheck extends HealthCheck {

    private HelloWorldConfiguration configuration;

    protected GetCardsForBoardHealthCheck(HelloWorldConfiguration configuration) {
        super("GetCardsForBoardHealthCheck")
        this.configuration = configuration
    }

    @Override
    protected Result check() throws Exception {

        int port = configuration.getHttpConfiguration().getPort();
        String url = "http://localhost:${port}/"
        def restClient = new RESTClient( url )

//        String path = 'card/?boardId=61&location=1'
        String path = 'hello-world'

        restClient.headers = [Accept: 'application/json']

//        def response = restClient.get( path : path )


        def response = doRestCall {
            restClient.get( path : path, requestContentType : 'application/json' )
        }


//        def expectedResponse = [
//                cards: [
//                        [
//                                id: 5939,
//                                text: 'order vitamins'
//                        ]
//                    ]
//                ]

        def expectedResponse = [
                    id: 1,
                    content: 'Hello, Stranger!'
        ]

        assertThat response.status, equalTo(200)
        try {
            assertReflectionEquals(expectedResponse, response.responseData)
        }
        catch (AssertionFailedError assertionFailedError) {
            return Result.unhealthy(assertionFailedError.getMessage())
        }


        return Result.healthy()


//        5939 order vitamins                                                                                                                                                                                                                                                           1         61
//        2062 Paint outside of patio door                                                                                                                                                                                                                                              1         61
//        5493 eamil Tara                                                                                                                                                                                                                                                               1         61
//        5494 reply to other emails                                                                                                                                                                                                                                                    1         61
//        5841 Buy Radon detector for Christmas                                                                                                                                                                                                                                         1         61
//        5861 prep for early voting                                                                                                                                                                                                                                                    1         61
//        5524 email about coffee with girls                                                                                                                                                                                                                                            1         61
//        5001 Spray paint hardward                                                                                                                                                                                                                                                     1         61
//        5402 clean hood, install screws                                                                                                                                                                                                                                               1         61
//        5374 file Teresa's lease                                                                                                                                                                                                                                                      1         61
//        5419 send email about welcome packages                                                                                                                                                                                                                                        1         61


    }

    static def doRestCall(def closure) {
        try {
            return closure()
        } catch (HttpResponseException hrex) {
            return hrex.response
        }
    }


}

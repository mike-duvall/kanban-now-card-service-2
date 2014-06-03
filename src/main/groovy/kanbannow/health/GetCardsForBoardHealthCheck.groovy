package kanbannow.health

import com.yammer.dropwizard.db.DatabaseConfiguration
import groovy.sql.Sql
import kanbannow.CardServiceConfiguration
import com.yammer.metrics.core.HealthCheck
import groovyx.net.http.HttpResponseException

import static org.hamcrest.CoreMatchers.equalTo
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals

import groovyx.net.http.RESTClient

import static org.junit.Assert.assertThat

import com.yammer.metrics.core.HealthCheck.Result

public class GetCardsForBoardHealthCheck extends HealthCheck {

    private CardServiceConfiguration configuration

    Sql sql

    public GetCardsForBoardHealthCheck(CardServiceConfiguration configuration) {
        super("GetCardsForBoardHealthCheck")
        this.configuration = configuration
    }




    @Override
    protected Result check() throws Exception {


        try {
            insertTestData()


            int port = configuration.getHttpConfiguration().getPort();
            String url = "http://localhost:${port}/"
            def restClient = new RESTClient(url)

//            String path = 'card?boardId=61&location=1'
            String path = 'card'

            restClient.headers = [Accept: 'application/json']


            def response = makeRESTCall {
                restClient.get(path: path, query: [boardId: 66, locationId: 1], requestContentType: 'application/json')
            }


            def expectedResponse = [
                    [
                            id  : 9999,
                            text: 'order vitamins'
                    ]
            ]

            try {
                assertThat response.status, equalTo(200)
                assertReflectionEquals(expectedResponse, response.responseData)
            }
            catch (Throwable assertionFailedError) {
                return Result.unhealthy(assertionFailedError.getMessage())
            }

            return Result.healthy()
        }
        finally {
            cleanUpTestData()
        }


    }


    private void insertTestData() {

        DatabaseConfiguration databaseConfiguration = configuration.getDatabaseConfiguration()

        sql = Sql.newInstance(
                databaseConfiguration.url,
                databaseConfiguration.user,
                databaseConfiguration.password,
                databaseConfiguration.driverClass )


        def text = "order vitamins"
        def boardId = 66
        def location = 1
        sql.execute('insert into card (id, text, board_id, location) values (?,?,?,?)', [9999,text, boardId, location])

    }

    void cleanUpTestData() {
        sql.execute('delete from card where board_id = ?' , [66])
    }

    static def makeRESTCall(def closure) {
        try {
            return closure()
        } catch (HttpResponseException hrex) {
            return hrex.response
        }
    }

}

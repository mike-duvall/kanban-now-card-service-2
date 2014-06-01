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

//        String path = 'card/?boardId=61&location=1'
            String path = 'card'

            restClient.headers = [Accept: 'application/json']


            def response = makeRESTCall {
                restClient.get(path: path, requestContentType: 'application/json')
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

//    database:
//
//    # the name of your JDBC driver
//    driverClass: oracle.jdbc.driver.OracleDriver
//
//    # the username
//    user: kanban_now
//
//    # the password
//    password: password
//
//    # the JDBC URL
//    url: jdbc:oracle:thin:@192.168.1.7:1521/ORA11DEV
//
//    # the minimum number of connections to keep open
//    minSize: 1
//
//    # the maximum number of connections to keep open
//    maxSize: 2
//
//    # the SQL query to run when validating a connection's liveness
//    validationQuery: "/* MyService Health Check */ SELECT 1 from dual"



    private void insertTestData() {

        DatabaseConfiguration databaseConfiguration = configuration.getDatabaseConfiguration()

//        Sql sql = Sql.newInstance( 'jdbc:jtds:sqlserver://serverName/dbName-CLASS;domain=domainName', 'username', 'password', 'net.sourceforge.jtds.jdbc.Driver' )
        sql = Sql.newInstance(
                databaseConfiguration.url,
                databaseConfiguration.user,
                databaseConfiguration.password,
                databaseConfiguration.driverClass )


        def text = "order vitamins"
        def boardId = 66
        def location = 1
        sql.execute('insert into card (id, text, board_id, location) values (?,?,?,?)', [9999,text, boardId, location])

        int x = 3
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
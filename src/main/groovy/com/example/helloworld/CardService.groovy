package com.example.helloworld

import com.example.helloworld.auth.ExampleAuthenticator
import com.example.helloworld.cli.RenderCommand
import com.example.helloworld.core.Template
import com.example.helloworld.core.User
import com.example.helloworld.health.GetCardsForBoardHealthCheck
import com.example.helloworld.health.TemplateHealthCheck
import com.example.helloworld.resources.CardResource
import com.example.helloworld.resources.HelloWorldResource
import com.example.helloworld.resources.ProtectedResource
import com.yammer.dropwizard.Service
import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.auth.basic.BasicAuthProvider
import com.yammer.dropwizard.config.Bootstrap
import com.yammer.dropwizard.config.Environment

public class CardService extends Service<CardServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new CardService().run(args)
    }

//    final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
//        new HibernateBundle<HelloWorldConfiguration>(ImmutableList.copyOf([Person]), new SessionFactoryFactory()) {
//            @Override
//            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration configuration) {
//                return configuration.getDatabaseConfiguration()
//            }
//        }

    @Override
    public void initialize(Bootstrap<CardServiceConfiguration> bootstrap) {
        bootstrap.setName("hello-world")
        bootstrap.addCommand(new RenderCommand())
        bootstrap.addBundle(new AssetsBundle())
//        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
//            @Override
//            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration configuration) {
//                return configuration.getDatabaseConfiguration()
//            }
//        })
//        bootstrap.addBundle(hibernateBundle)
    }

    @Override
    public void run(CardServiceConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
//        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory())

        environment.addProvider(new BasicAuthProvider<User>(new ExampleAuthenticator(), "SUPER SECRET STUFF"))

        final Template template = configuration.buildTemplate()

        environment.addHealthCheck(new TemplateHealthCheck(template))
        environment.addHealthCheck(new GetCardsForBoardHealthCheck(configuration))
        environment.addResource(new HelloWorldResource(template))
        environment.addResource(new ProtectedResource())
//        environment.addResource(new PeopleResource(dao))
//        environment.addResource(new PersonResource(dao))
        environment.addResource(new CardResource())

    }
}

package nl.inholland.hello;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.inholland.hello.model.User;

public class HelloApplication extends Application<HelloConfiguration>
{
    private GuiceBundle<HelloConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception
    {
        new HelloApplication().run(new String[] { "server" } );
    }

    @Override
    public void initialize( final Bootstrap<HelloConfiguration> bootstrap)
    {
        configureGuice();
        bootstrap.addBundle(guiceBundle);
    }

    private void configureGuice()
    {
        guiceBundle = GuiceBundle.<HelloConfiguration>newBuilder()
            .addModule( new HelloModule() )
            .enableAutoConfig(getClass().getPackage().getName())
            .setConfigClass(HelloConfiguration.class)
            .build();
    }

    @Override
    public void run( final HelloConfiguration configuration, 
                     final Environment environment) throws Exception
    {
        environment.jersey().register(
            new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                    .setAuthenticator(new HelloAuthenticator())
                    .setRealm("SUPER SECRET STUFF")
                    .buildAuthFilter() ));
        
        environment.jersey().register(
            new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
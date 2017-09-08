package nl.inholland.hello;

import nl.inholland.hello.resource.HelloResource;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class HelloModule extends AbstractModule
{
    @Override
    protected void configure()
    {
    }
    
    @Provides
    public HelloResource providesHelloResource(HelloConfiguration configuration) {
        return new HelloResource(configuration.getDefaultName());
    }

}
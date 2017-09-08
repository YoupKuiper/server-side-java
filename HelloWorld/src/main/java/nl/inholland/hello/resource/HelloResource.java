package nl.inholland.hello.resource;

import io.dropwizard.auth.Auth;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.inholland.hello.model.User;

@Path("/")
public class HelloResource
{
    private String name;
    
    public HelloResource()
    {
    }

    public HelloResource(String name) {
        this.name = name;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getName( @Auth User user)
    {
        return String.format(
                "Authenticator: %s",
                user.getName());
    }
}
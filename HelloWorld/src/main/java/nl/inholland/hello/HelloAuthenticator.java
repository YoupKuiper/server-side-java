package nl.inholland.hello;

import nl.inholland.hello.model.User;
import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.ArrayList;
import java.util.List;

public class HelloAuthenticator implements Authenticator<BasicCredentials, User>
{
    ArrayList<User> users = new ArrayList<User>();

    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        users.add(new User("Hans", "Klok", false));
        users.add(new User("Hans", "Klok", false));
        users.add(new User("Youp", "test", true));
        users.add(new User("Hans", "Klok", false));
        users.add(new User("Hans", "Klok", false));

        User user = new User(credentials.getUsername(), credentials.getPassword());

        for( User userFromDB : users){
            if (user.getName().equals(userFromDB.getName()) && user.getPassword().equals(userFromDB.getPassword())) {
                if(userFromDB.isImportant()){
                    return Optional.of(new User(user.getName() + " de belangrijke", user.getPassword()));
                }else{
                    return Optional.of(user);
                }
                      
            }
        }

   
        return Optional.empty();
    }
}
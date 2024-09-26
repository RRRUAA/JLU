package javaee.restws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class TravelRESTWSApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public TravelRESTWSApplication(){
        singletons.add(new TravelWS());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

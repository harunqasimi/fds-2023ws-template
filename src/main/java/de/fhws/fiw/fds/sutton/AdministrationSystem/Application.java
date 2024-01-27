package de.fhws.fiw.fds.sutton.AdministrationSystem;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.service.*;
import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath( "api" )
public class Application extends AbstractApplication {
    @Override protected Set<Class<?>> getServiceClasses( )
        {
            final Set<Class<?>> returnValue = new HashSet<>( );
                returnValue.add(SeriesService.class);
                returnValue.add(SeasonService.class);
                returnValue.add(EpisodeService.class);
                returnValue.add(ActorService.class);
                returnValue.add(DispatcherService.class);

            return returnValue;
        }
}

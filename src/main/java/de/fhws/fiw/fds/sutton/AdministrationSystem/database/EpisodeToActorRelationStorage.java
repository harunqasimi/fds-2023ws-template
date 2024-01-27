package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;

public class EpisodeToActorRelationStorage extends AbstractInMemoryRelationStorage<Actor>
        implements EpisodeActorDao
                {
                    public EpisodeToActorRelationStorage( )
                    {
                        super( );
                        this.storage.put( 1l, 1l );
                    }

                    @Override protected IDatabaseAccessObject<Actor> getSecondaryStorage( )
                    {
                        return DaoFactory.getInstance( ).getActorDao( );
                    }
}

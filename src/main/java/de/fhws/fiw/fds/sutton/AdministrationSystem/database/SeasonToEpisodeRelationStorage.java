package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;

import java.util.function.Predicate;

public class SeasonToEpisodeRelationStorage extends AbstractInMemoryRelationStorage<Episode>
        implements SeasonEpisodeDao
{
    public SeasonToEpisodeRelationStorage( )
    {
        super( );
        this.storage.put( 1l, 1l );
    }

    @Override protected IDatabaseAccessObject<Episode> getSecondaryStorage( )
    {
        return DaoFactory.getInstance( ).getEpisodeDao( );
    }

}

package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;

public class SeriesToSeasonRelationStorage extends AbstractInMemoryRelationStorage<Season>
        implements SeriesSeasonDao {
    public SeriesToSeasonRelationStorage( ) {
        super( );
        this.storage.put( 1l, 1l );
    }

    @Override 
    protected IDatabaseAccessObject<Season> getSecondaryStorage( )
    {
        return DaoFactory.getInstance( ).getSeasonDao( );
    }


}

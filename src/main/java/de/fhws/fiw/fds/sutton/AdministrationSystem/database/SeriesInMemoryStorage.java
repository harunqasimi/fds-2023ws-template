package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

import java.time.LocalDate;

public class SeriesInMemoryStorage extends AbstractInMemoryStorage<Series> implements SeriesDao
{
    public SeriesInMemoryStorage( )
    {

        super( );
        populateData( );
    }

    private void populateData( )
    {
    create( new Series (
            "One Piece", "Japan", "Adventure", "20 min", LocalDate.of(1999, 01, 01)));
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

import java.time.LocalDate;

public class EpisodeInMemoryStorage extends AbstractInMemoryStorage<Episode> implements EpisodeDao
{
    public EpisodeInMemoryStorage( )
    {
        super( );
        populateData( );
    }

    private void populateData( ) {
    create(new Episode(
      1, "The Start", "Max", LocalDate.of(1999,01,01)  ));
    }
}
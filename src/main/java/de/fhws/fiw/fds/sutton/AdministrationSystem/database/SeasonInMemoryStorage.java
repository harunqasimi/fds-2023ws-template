package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

import java.time.LocalDate;

public class SeasonInMemoryStorage extends AbstractInMemoryStorage<Season> implements SeasonDao {
    public SeasonInMemoryStorage() {
        super();
        populateData();
    }

    private void populateData() {
        create(new Season(
                1, 1999));
    }
}

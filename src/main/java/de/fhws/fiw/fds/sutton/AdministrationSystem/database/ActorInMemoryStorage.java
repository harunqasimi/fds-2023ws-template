package de.fhws.fiw.fds.sutton.AdministrationSystem.database;

import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

public class ActorInMemoryStorage extends AbstractInMemoryStorage<Actor> implements ActorDao {
    public ActorInMemoryStorage() {
        super();
        populateData();
    }

    private void populateData() {
    create(new Actor(
    "Angelina", "Jolie", "Nico", "Robin"));
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors;

import de.fhws.fiw.fds.sutton.AdministrationSystem.Start;

public interface ActorUri {
    String PATH_ELEMENT = "actor";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}

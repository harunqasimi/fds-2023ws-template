package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season;

import de.fhws.fiw.fds.sutton.AdministrationSystem.Start;

public interface SeasonUri {
    String PATH_ELEMENT = "season";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}

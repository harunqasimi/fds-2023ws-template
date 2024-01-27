package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.Start;

public interface EpisodeUri {
    String PATH_ELEMENT = "episode";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}

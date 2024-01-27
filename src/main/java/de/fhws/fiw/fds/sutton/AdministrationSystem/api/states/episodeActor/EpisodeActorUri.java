package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

import de.fhws.fiw.fds.sutton.AdministrationSystem.Start;

public interface EpisodeActorUri {
    String SHOW_ALL_PARAMETER = "showAll";
    String PATH_ELEMENT = "episode/{id}/actor";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
    String REL_PATH_SHOW_ONLY_LINKED =
            Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
    String REL_PATH_ID = REL_PATH + "/{id}";
}

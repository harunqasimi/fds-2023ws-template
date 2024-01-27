package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

import de.fhws.fiw.fds.sutton.AdministrationSystem.Start;

public interface SeriesSeasonUri {
    String SHOW_ALL_PARAMETER = "showAll";
    String PATH_ELEMENT = "series/{id}/season";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
    String REL_PATH_SHOW_ONLY_LINKED =
            Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
    String REL_PATH_ID = REL_PATH + "/{id}";
}

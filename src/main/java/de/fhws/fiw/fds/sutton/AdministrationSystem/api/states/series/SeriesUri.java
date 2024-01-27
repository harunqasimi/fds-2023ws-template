package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series;

import de.fhws.fiw.fds.sutton.AdministrationSystem.Start;

public interface SeriesUri {
    String PATH_ELEMENT = "series";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}

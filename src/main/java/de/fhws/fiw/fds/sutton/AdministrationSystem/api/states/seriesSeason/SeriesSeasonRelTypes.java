package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

public interface SeriesSeasonRelTypes {
    String CREATE_SEASON = "createSeasonOfSeries";
    String GET_ALL_LINKED_SEASON = "getAllSeasonsOfSeries";
    String GET_ALL_SEASON = "getAllLinkableSeasons";
    String UPDATE_SINGLE_SEASON = "updateSeasonOfSeries";
    String CREATE_LINK_FROM_SERIES_TO_SEASON = "linkSeriesToSeason";
    String DELETE_LINK_FROM_SERIES_TO_SEASON = "unlinkSeriesToSeason";
    String GET_SINGLE_SEASON = "getSeasonOfSeries";
}

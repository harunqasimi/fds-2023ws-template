package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode;

public interface SeasonEpisodeRelTypes {
    String CREATE_EPISODE = "createEpisodeOfSeason";
    String GET_ALL_LINKED_EPISODE = "getAllEpisodesOfSeason";
    String GET_ALL_EPISODE = "getAllLinkableEpisodes";
    String UPDATE_SINGLE_EPISODE = "updateEpisodeOfSeason";
    String CREATE_LINK_FROM_SEASON_TO_EPISODE = "linkSeasonToEpisode";
    String DELETE_LINK_FROM_SEASON_TO_EPISODE = "unlinkSeasonToEpisode";
    String GET_SINGLE_EPISODE = "getEpisodeOfSeason";
}

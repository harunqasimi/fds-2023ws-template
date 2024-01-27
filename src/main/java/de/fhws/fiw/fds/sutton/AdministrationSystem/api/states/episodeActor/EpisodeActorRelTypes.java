package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

public interface EpisodeActorRelTypes {
    String CREATE_ACTOR = "createActorOfEpisode";
    String GET_ALL_LINKED_ACTOR = "getAllActorsOfEpisode";
    String GET_ALL_ACTOR = "getAllLinkableActors";
    String UPDATE_SINGLE_ACTOR = "updateActorOfEpisode";
    String CREATE_LINK_FROM_EPISODE_TO_ACTOR = "linkEpisodeToActor";
    String DELETE_LINK_FROM_EPISODE_TO_ACTOR = "unlinkEpisodeToActor";
    String GET_SINGLE_ACTOR = "getActorOfEpisode";
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors.ActorRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors.ActorUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode.EpisodeRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode.EpisodeUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season.SeasonRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season.SeasonUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series.SeriesRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series.SeriesUri;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;

import javax.ws.rs.core.MediaType;

public class DispatcherState extends AbstractGetDispatcherState {
    public DispatcherState(final Builder builder) {
        super(builder);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(SeriesUri.REL_PATH, SeriesRelTypes.CREATE_SERIES, MediaType.APPLICATION_JSON);
        addLink(SeriesUri.REL_PATH, SeriesRelTypes.GET_ALL_SERIES, MediaType.APPLICATION_JSON);
        addLink(SeasonUri.REL_PATH, SeasonRelTypes.CREATE_SEASON, MediaType.APPLICATION_JSON);
        addLink(SeasonUri.REL_PATH, SeasonRelTypes.GET_ALL_SEASON, MediaType.APPLICATION_JSON);
        addLink(EpisodeUri.REL_PATH, EpisodeRelTypes.CREATE_EPISODE, MediaType.APPLICATION_JSON);
        addLink(EpisodeUri.REL_PATH, EpisodeRelTypes.GET_ALL_EPISODE, MediaType.APPLICATION_JSON);
        addLink(ActorUri.REL_PATH, ActorRelTypes.CREATE_ACTOR, MediaType.APPLICATION_JSON);
        addLink(ActorUri.REL_PATH, ActorRelTypes.GET_ALL_ACTOR, MediaType.APPLICATION_JSON);

    }

    public static class Builder extends AbstractDispatcherStateBuilder {
        @Override public AbstractState build()
        {
            return new DispatcherState(this);
        }
    }
}

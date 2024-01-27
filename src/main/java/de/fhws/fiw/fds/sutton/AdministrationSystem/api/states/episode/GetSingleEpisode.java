package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleEpisode extends AbstractGetState<Episode>
{
    public GetSingleEpisode(final AbstractGetState.AbstractGetStateBuilder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Episode> loadModel() {
        return DaoFactory.getInstance().getEpisodeDao().readById(this.requestedId);
    }

    @Override
    protected void defineHttpResponseBody( )
    {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity( this.requestedModel.getResult( ) );
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(EpisodeUri.REL_PATH_ID, EpisodeRelTypes.UPDATE_SINGLE_EPISODE, getAcceptRequestHeader(),
                this.requestedId);
        addLink(EpisodeUri.REL_PATH_ID, EpisodeRelTypes.DELETE_SINGLE_EPISODE, getAcceptRequestHeader(),
                this.requestedId);
    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetSingleEpisode(this);

        }
    }
}

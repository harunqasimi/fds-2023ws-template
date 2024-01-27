package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleSeason extends AbstractGetState<Season>
{
    public GetSingleSeason(final AbstractGetState.AbstractGetStateBuilder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
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
    protected SingleModelResult<Season> loadModel() {
        return DaoFactory.getInstance().getSeasonDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(SeasonUri.REL_PATH_ID, SeasonRelTypes.UPDATE_SINGLE_SEASON, getAcceptRequestHeader( ),
                this.requestedId);
        addLink(SeasonUri.REL_PATH_ID, SeasonRelTypes.DELETE_SINGLE_SEASON, getAcceptRequestHeader( ),
                this.requestedId);
    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetSingleSeason(this);

        }
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleSeries extends AbstractGetState<Series>
{
    public GetSingleSeries(final AbstractGetState.AbstractGetStateBuilder builder) {
        super(builder);
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
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Series> loadModel() {
        return DaoFactory.getInstance().getSeriesDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(SeriesUri.REL_PATH_ID, SeriesRelTypes.UPDATE_SINGLE_SERIES, getAcceptRequestHeader( ),
                this.requestedId);
        addLink(SeriesUri.REL_PATH_ID, SeriesRelTypes.DELETE_SINGLE_SERIES, getAcceptRequestHeader( ),
                this.requestedId);
    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetSingleSeries(this);

        }
    }
}

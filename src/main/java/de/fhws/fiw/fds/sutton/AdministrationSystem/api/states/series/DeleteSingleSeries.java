package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleSeries extends AbstractDeleteState<Series>
{
    public DeleteSingleSeries(final Builder builder) {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Series> loadModel() {
        return DaoFactory.getInstance().getSeriesDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getSeriesDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink( SeriesUri.REL_PATH, SeriesRelTypes.GET_ALL_SERIES, getAcceptRequestHeader());
    }

    public static class Builder extends AbstractDeleteState.AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new DeleteSingleSeries(this);
        }

    }
}
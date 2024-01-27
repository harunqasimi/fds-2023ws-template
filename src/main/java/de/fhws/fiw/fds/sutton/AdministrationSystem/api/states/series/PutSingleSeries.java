package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleSeries extends AbstractPutState<Series> {
    public PutSingleSeries(final Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Series> loadModel() {
        return DaoFactory.getInstance().getSeriesDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel( ) {
        return DaoFactory.getInstance().getSeriesDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(SeriesUri.REL_PATH_ID, SeriesRelTypes.GET_SINGLE_SERIES, getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }

    public static class Builder extends AbstractPutStateBuilder<Series>
    {
        @Override
        public AbstractState build() {
            return new PutSingleSeries(this);
        }
    }
}

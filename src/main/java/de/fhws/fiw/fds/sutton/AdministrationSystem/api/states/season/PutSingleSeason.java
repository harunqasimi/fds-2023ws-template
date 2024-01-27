package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleSeason extends AbstractPutState<Season> {
    public PutSingleSeason(final PutSingleSeason.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Season> loadModel() {
        return DaoFactory.getInstance().getSeasonDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel( ) {
        return DaoFactory.getInstance().getSeasonDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(SeasonUri.REL_PATH_ID, SeasonRelTypes.GET_SINGLE_SEASON, getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }

    public static class Builder extends AbstractPutStateBuilder<Season>
    {
        @Override
        public AbstractState build() {
            return new PutSingleSeason(this);
        }
    }
}

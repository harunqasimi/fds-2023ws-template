package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleSeason extends AbstractDeleteState<Season>
{
    public DeleteSingleSeason(final DeleteSingleSeason.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Season> loadModel() {
        return DaoFactory.getInstance().getSeasonDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getSeasonDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink( SeasonUri.REL_PATH, SeasonRelTypes.GET_ALL_SEASON, getAcceptRequestHeader());
    }

    public static class Builder extends AbstractDeleteState.AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new DeleteSingleSeason(this);
        }

    }
}

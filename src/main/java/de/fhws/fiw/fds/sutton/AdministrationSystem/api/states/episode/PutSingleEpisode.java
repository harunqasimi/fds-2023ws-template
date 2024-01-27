package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleEpisode extends AbstractPutState<Episode> {
    public PutSingleEpisode(final PutSingleEpisode.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Episode> loadModel() {
        return DaoFactory.getInstance().getEpisodeDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel( ) {
        return DaoFactory.getInstance().getEpisodeDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(EpisodeUri.REL_PATH_ID, EpisodeRelTypes.GET_SINGLE_EPISODE, getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }

    public static class Builder extends AbstractPutStateBuilder<Episode>
    {
        @Override
        public AbstractState build() {
            return new PutSingleEpisode(this);
        }
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleEpisode extends AbstractDeleteState<Episode>
{
    public DeleteSingleEpisode(final DeleteSingleEpisode.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Episode> loadModel() {
        return DaoFactory.getInstance().getEpisodeDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getEpisodeDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(EpisodeUri.REL_PATH, EpisodeRelTypes.GET_ALL_EPISODE, getAcceptRequestHeader());
    }

    public static class Builder extends AbstractDeleteState.AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new DeleteSingleEpisode(this);
        }

    }
}

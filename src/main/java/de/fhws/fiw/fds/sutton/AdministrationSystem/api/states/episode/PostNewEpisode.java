package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewEpisode extends AbstractPostState<Episode> {
    public PostNewEpisode(final PostNewEpisode.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected NoContentResult saveModel()
    {
        return DaoFactory.getInstance().getEpisodeDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
    }

    public static class Builder extends AbstractPostStateBuilder<Episode> {
        @Override
        public AbstractState build() {
            return new PostNewEpisode(this);
        }
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewSeason extends AbstractPostState<Season> {
    public PostNewSeason(final PostNewSeason.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected NoContentResult saveModel()
    {
        return DaoFactory.getInstance().getSeasonDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
    }

    public static class Builder extends AbstractPostStateBuilder<Season> {
        @Override
        public AbstractState build() {
            return new PostNewSeason(this);
        }
    }
}

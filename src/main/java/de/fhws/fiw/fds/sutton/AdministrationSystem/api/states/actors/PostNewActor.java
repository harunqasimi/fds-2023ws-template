package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewActor extends AbstractPostState<Actor> {
    public PostNewActor(final PostNewActor.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected NoContentResult saveModel()
    {
        return DaoFactory.getInstance().getActorDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
    }

    public static class Builder extends AbstractPostStateBuilder<Actor> {
        @Override
        public AbstractState build() {
            return new PostNewActor(this);
        }
    }
}

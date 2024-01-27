package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors;


import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleActor extends AbstractDeleteState<Actor>
{
    public DeleteSingleActor(final DeleteSingleActor.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Actor> loadModel() {
        return DaoFactory.getInstance().getActorDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getActorDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(ActorUri.REL_PATH, ActorRelTypes.GET_ALL_ACTOR, getAcceptRequestHeader());
    }

    public static class Builder extends AbstractDeleteState.AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new DeleteSingleActor(this);
        }

    }
}

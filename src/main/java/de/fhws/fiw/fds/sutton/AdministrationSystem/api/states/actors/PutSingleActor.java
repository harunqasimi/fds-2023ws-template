package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors;


import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleActor extends AbstractPutState<Actor> {
    public PutSingleActor(final PutSingleActor.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected SingleModelResult<Actor> loadModel() {
        return DaoFactory.getInstance().getActorDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel( ) {
        return DaoFactory.getInstance().getActorDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(ActorUri.REL_PATH_ID, ActorRelTypes.GET_SINGLE_ACTOR, getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }


    public static class Builder extends AbstractPutStateBuilder<Actor>
    {
        @Override
        public AbstractState build() {
            return new PutSingleActor(this);
        }
    }
}

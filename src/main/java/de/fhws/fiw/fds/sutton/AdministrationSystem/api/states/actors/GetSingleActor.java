package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleActor extends AbstractGetState<Actor>
{
    public GetSingleActor(final AbstractGetState.AbstractGetStateBuilder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected void defineHttpResponseBody( )
    {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity( this.requestedModel.getResult( ) );
    }

    @Override
    protected SingleModelResult<Actor> loadModel() {
        return DaoFactory.getInstance().getActorDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(ActorUri.REL_PATH_ID, ActorRelTypes.UPDATE_SINGLE_ACTOR, getAcceptRequestHeader(),
                this.requestedId);
        addLink(ActorUri.REL_PATH_ID, ActorRelTypes.DELETE_SINGLE_ACTOR, getAcceptRequestHeader(),
                this.requestedId);
    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetSingleActor(this);

        }
    }
}

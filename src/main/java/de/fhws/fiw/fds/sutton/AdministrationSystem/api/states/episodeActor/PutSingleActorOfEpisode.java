package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor.PutSingleActorOfEpisode;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor.EpisodeActorRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor.EpisodeActorUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleActorOfEpisode extends AbstractPutRelationState<Actor>
{
    public PutSingleActorOfEpisode( final PutSingleActorOfEpisode.Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Actor> loadModel( )
    {
        return DaoFactory.getInstance( ).getActorDao( ).readById( this.requestedId );
    }

    @Override protected NoContentResult updateModel( )
    {
        return DaoFactory.getInstance( ).getEpisodeActorDao( ).update( this.primaryId, this.modelToUpdate );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( EpisodeActorUri.REL_PATH_ID,
                EpisodeActorRelTypes.GET_SINGLE_ACTOR,
                getAcceptRequestHeader( ),
                this.primaryId, this.requestedId );
    }

    public static class Builder extends AbstractPutRelationStateBuilder<Actor>
    {
        @Override public AbstractState build( )
        {
            return new PutSingleActorOfEpisode( this );
        }
    }
}

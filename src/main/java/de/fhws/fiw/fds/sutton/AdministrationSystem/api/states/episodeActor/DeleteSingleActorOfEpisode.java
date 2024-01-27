package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleActorOfEpisode extends AbstractDeleteRelationState<Actor>
{
    public DeleteSingleActorOfEpisode( final DeleteSingleActorOfEpisode.Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Actor> loadModel( )
    {
        return DaoFactory.getInstance( ).getEpisodeActorDao( ).readById( this.primaryId, this.modelIdToDelete );
    }

    @Override protected NoContentResult deleteModel( )
    {
        return DaoFactory.getInstance( ).getEpisodeActorDao( ).deleteRelation( this.primaryId, this.modelIdToDelete );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( EpisodeActorUri.REL_PATH,
                EpisodeActorRelTypes.GET_ALL_LINKED_ACTOR,
                getAcceptRequestHeader( ),
                this.primaryId );
    }

    public static class Builder extends AbstractDeleteRelationStateBuilder
    {
        @Override public AbstractState build( )
        {
            return new DeleteSingleActorOfEpisode( this );
        }
    }
}

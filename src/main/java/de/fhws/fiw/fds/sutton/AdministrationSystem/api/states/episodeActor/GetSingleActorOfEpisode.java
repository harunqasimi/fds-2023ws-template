package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleActorOfEpisode extends AbstractGetRelationState<Actor>
{
    public GetSingleActorOfEpisode( final GetSingleActorOfEpisode.Builder builder )
    {
        super( builder );
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
    protected SingleModelResult<Actor> loadModel( )
    {
        return DaoFactory.getInstance( ).getActorDao( ).readById( this.requestedId );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink(EpisodeActorUri.REL_PATH_SHOW_ONLY_LINKED,
                EpisodeActorRelTypes.GET_ALL_LINKED_ACTOR,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( isEpisodeLinkedToThisActor( ) )
        {
            addLink( EpisodeActorUri.REL_PATH_ID,
                    EpisodeActorRelTypes.UPDATE_SINGLE_ACTOR,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );

            addLink( EpisodeActorUri.REL_PATH_ID,
                    EpisodeActorRelTypes.DELETE_LINK_FROM_EPISODE_TO_ACTOR,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
        else
        {
            addLink( EpisodeActorUri.REL_PATH_ID,
                    EpisodeActorRelTypes.CREATE_LINK_FROM_EPISODE_TO_ACTOR,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
    }

    private boolean isEpisodeLinkedToThisActor( )
    {
        return !DaoFactory.getInstance( )
                .getEpisodeActorDao( )
                .readById( this.primaryId, this.requestedId )
                .isEmpty( );
    }

    public static class Builder extends AbstractGetRelationState.AbstractGetRelationStateBuilder
    {
        @Override
        public AbstractState build( )
        {
            return new GetSingleActorOfEpisode( this );
        }
    }
}

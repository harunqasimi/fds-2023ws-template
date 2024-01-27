package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor.PostNewActorOfEpisode;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewActorOfEpisode extends AbstractPostRelationState<Actor>
{
    public PostNewActorOfEpisode( final PostNewActorOfEpisode.Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getEpisodeActorDao( ).create( this.primaryId, this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

    }

    public static class Builder extends AbstractPostRelationStateBuilder<Actor>
    {
        @Override public AbstractState build( )
        {
            return new PostNewActorOfEpisode( this );
        }
    }
}

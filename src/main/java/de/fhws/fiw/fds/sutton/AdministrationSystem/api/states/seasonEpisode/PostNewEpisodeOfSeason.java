package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewEpisodeOfSeason extends AbstractPostRelationState<Episode>
{
    public PostNewEpisodeOfSeason( final PostNewEpisodeOfSeason.Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getSeasonEpisodeDao( ).create( this.primaryId, this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

    }

    public static class Builder extends AbstractPostRelationStateBuilder<Episode>
    {
        @Override public AbstractState build( )
        {
            return new PostNewEpisodeOfSeason( this );
        }
    }
}

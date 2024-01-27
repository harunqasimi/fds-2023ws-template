package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewSeasonOfSeries extends AbstractPostRelationState<Season>
{
    public PostNewSeasonOfSeries( final Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance( ).getSeriesSeasonDao( ).create( this.primaryId, this.modelToStore );
    }

    @Override protected void defineTransitionLinks( )
    {

    }

    public static class Builder extends AbstractPostRelationStateBuilder<Season>
    {
        @Override public AbstractState build( )
        {
            return new PostNewSeasonOfSeries( this );
        }
    }
}

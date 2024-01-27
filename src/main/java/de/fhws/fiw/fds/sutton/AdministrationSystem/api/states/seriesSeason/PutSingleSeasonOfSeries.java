package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleSeasonOfSeries extends AbstractPutRelationState<Season>
{
    public PutSingleSeasonOfSeries( final Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Season> loadModel( )
    {
        return DaoFactory.getInstance( ).getSeasonDao( ).readById( this.requestedId );
    }

    @Override protected NoContentResult updateModel( )
    {
        return DaoFactory.getInstance( ).getSeriesSeasonDao( ).update( this.primaryId, this.modelToUpdate );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( SeriesSeasonUri.REL_PATH_ID,
                SeriesSeasonRelTypes.GET_SINGLE_SEASON,
                getAcceptRequestHeader( ),
                this.primaryId, this.requestedId );
    }

    public static class Builder extends AbstractPutRelationStateBuilder<Season>
    {
        @Override public AbstractState build( )
        {
            return new PutSingleSeasonOfSeries( this );
        }
    }
}

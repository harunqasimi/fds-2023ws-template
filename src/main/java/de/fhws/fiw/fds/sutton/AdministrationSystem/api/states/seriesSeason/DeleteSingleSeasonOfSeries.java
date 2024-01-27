package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleSeasonOfSeries extends AbstractDeleteRelationState<Season>
{
    public DeleteSingleSeasonOfSeries( final Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Season> loadModel( )
    {
        return DaoFactory.getInstance( ).getSeriesSeasonDao( ).readById( this.primaryId, this.modelIdToDelete );
    }

    @Override protected NoContentResult deleteModel( )
    {
        return DaoFactory.getInstance( ).getSeriesSeasonDao( ).deleteRelation( this.primaryId, this.modelIdToDelete );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( SeriesSeasonUri.REL_PATH,
                SeriesSeasonRelTypes.GET_ALL_LINKED_SEASON,
                getAcceptRequestHeader( ),
                this.primaryId );
    }

    public static class Builder extends AbstractDeleteRelationStateBuilder
    {
        @Override public AbstractState build( )
        {
            return new DeleteSingleSeasonOfSeries( this );
        }
    }
}

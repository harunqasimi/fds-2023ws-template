package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleSeasonOfSeries extends AbstractGetRelationState<Season>
{
    public GetSingleSeasonOfSeries( final Builder builder )
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
    protected SingleModelResult<Season> loadModel( )
    {
        return DaoFactory.getInstance( ).getSeasonDao( ).readById( this.requestedId );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink(SeriesSeasonUri.REL_PATH_SHOW_ONLY_LINKED,
                SeriesSeasonRelTypes.GET_ALL_LINKED_SEASON,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( isSeriesLinkedToThisSeason( ) )
        {
            addLink( SeriesSeasonUri.REL_PATH_ID,
                    SeriesSeasonRelTypes.UPDATE_SINGLE_SEASON,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );

            addLink( SeriesSeasonUri.REL_PATH_ID,
                    SeriesSeasonRelTypes.DELETE_LINK_FROM_SERIES_TO_SEASON,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
        else
        {
            addLink( SeriesSeasonUri.REL_PATH_ID,
                    SeriesSeasonRelTypes.CREATE_LINK_FROM_SERIES_TO_SEASON,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
    }

    private boolean isSeriesLinkedToThisSeason( )
    {
        return !DaoFactory.getInstance( )
                .getSeriesSeasonDao( )
                .readById( this.primaryId, this.requestedId )
                .isEmpty( );
    }

    public static class Builder extends AbstractGetRelationState.AbstractGetRelationStateBuilder
    {
        @Override
        public AbstractState build( )
        {
            return new GetSingleSeasonOfSeries( this );
        }
    }
}

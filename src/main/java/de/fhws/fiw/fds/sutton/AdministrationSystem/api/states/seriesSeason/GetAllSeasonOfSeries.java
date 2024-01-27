package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.SeriesSeasonDao;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;
import java.util.function.Predicate;

public class GetAllSeasonOfSeries extends AbstractGetCollectionRelationState<Season>
{
    public GetAllSeasonOfSeries( final Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {
        
    }

    @Override protected void defineHttpResponseBody( )
    {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity( new GenericEntity<Collection<Season>>( this.result.getResult( ) )
        {
        } );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( SeriesSeasonUri.REL_PATH,
                SeriesSeasonRelTypes.CREATE_SEASON,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( this.query.isShowAll( ) )
        {
            addLink( SeriesSeasonUri.REL_PATH_SHOW_ONLY_LINKED,
                    SeriesSeasonRelTypes.GET_ALL_LINKED_SEASON,
                    getAcceptRequestHeader( ),
                    this.primaryId );
        }
        else
        {
            addLink( SeriesSeasonUri.REL_PATH_SHOW_ALL,
                    SeriesSeasonRelTypes.GET_ALL_SEASON,
                    getAcceptRequestHeader( ),
                    this.primaryId );
        }
    }

    public static class AllSeason extends AbstractRelationQuery<Season>
    {
        private final SeriesSeasonDao storage;

        public AllSeason( final long primaryId, final boolean showAll )
        {
            super( primaryId, showAll );
            this.storage = DaoFactory.getInstance( ).getSeriesSeasonDao( );
        }

        @Override protected CollectionModelResult<Season> doExecuteQuery( ) throws DatabaseException
        {
            if ( showAll )
            {
                return storage.readAllByPredicate( this.primaryId, all( ) );
            }
            else
            {
                return this.storage.readByPredicate( this.primaryId, all( ) );
            }
        }
    }

    public static class FilterSeasonsByNumber extends AbstractRelationQuery<Season>
    {
        private final SeriesSeasonDao storage;
        private final int number;

        public FilterSeasonsByNumber( final long primaryId, final boolean showAll, final int number )
        {
            super( primaryId, showAll );
            this.number = number;
            this.storage = DaoFactory.getInstance( ).getSeriesSeasonDao( );
        }

        @Override protected CollectionModelResult<Season> doExecuteQuery( ) throws DatabaseException
        {
            if ( showAll )
            {
                return this.storage.readAllByPredicate( this.primaryId, byNumber( ) );
            }
            else
            {
                return this.storage.readByPredicate( this.primaryId, byNumber( ) );
            }
        }

        protected Predicate<Season> byNumber( )
        {
            return l ->  number == 0 || l.getNumber( ) == number;
        }
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Season>
    {
        @Override public AbstractState build( )
        {
            return new GetAllSeasonOfSeries( this );
        }
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor.GetAllActorOfEpisode;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.SeasonEpisodeDao;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.SeasonEpisodeDao;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;
import java.util.function.Predicate;

public class GetAllEpisodeOfSeason extends AbstractGetCollectionRelationState<Episode>
{
    public GetAllEpisodeOfSeason( final GetAllEpisodeOfSeason.Builder builder )
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
                .entity( new GenericEntity<Collection<Episode>>( this.result.getResult( ) )
                {
                } );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( SeasonEpisodeUri.REL_PATH,
                SeasonEpisodeRelTypes.CREATE_EPISODE,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( this.query.isShowAll( ) )
        {
            addLink( SeasonEpisodeUri.REL_PATH_SHOW_ONLY_LINKED,
                    SeasonEpisodeRelTypes.GET_ALL_LINKED_EPISODE,
                    getAcceptRequestHeader( ),
                    this.primaryId );
        }
        else
        {
            addLink( SeasonEpisodeUri.REL_PATH_SHOW_ALL,
                    SeasonEpisodeRelTypes.GET_ALL_EPISODE,
                    getAcceptRequestHeader( ),
                    this.primaryId );
        }
    }

    public static class AllSeason extends AbstractRelationQuery<Episode>
    {
        private final SeasonEpisodeDao storage;

        public AllSeason( final long primaryId, final boolean showAll )
        {
            super( primaryId, showAll );
            this.storage = DaoFactory.getInstance( ).getSeasonEpisodeDao( );
        }

        @Override protected CollectionModelResult<Episode> doExecuteQuery( ) throws DatabaseException
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

    public static class FilterEpisodesByNumber extends AbstractRelationQuery<Episode>
    {
        private final SeasonEpisodeDao storage;
        private final int number;

        public FilterEpisodesByNumber( final long primaryId, final boolean showAll, final int number )
        {
            super( primaryId, showAll );
            this.number = number;
            this.storage = DaoFactory.getInstance( ).getSeasonEpisodeDao( );
        }

        @Override protected CollectionModelResult<Episode> doExecuteQuery( ) throws DatabaseException
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

        protected Predicate<Episode> byNumber( )
        {
            return l ->  number == 0 || l.getNumber( ) == number;
        }
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Episode>
    {
        @Override public AbstractState build( )
        {
            return new GetAllEpisodeOfSeason( this );
        }
    }
}

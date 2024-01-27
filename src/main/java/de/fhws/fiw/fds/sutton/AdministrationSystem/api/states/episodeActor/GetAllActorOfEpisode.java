package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.EpisodeActorDao;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
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

public class GetAllActorOfEpisode extends AbstractGetCollectionRelationState<Actor>
{
    public GetAllActorOfEpisode( final GetAllActorOfEpisode.Builder builder )
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
                .entity( new GenericEntity<Collection<Actor>>( this.result.getResult( ) )
                {
                } );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( EpisodeActorUri.REL_PATH,
                EpisodeActorRelTypes.CREATE_ACTOR,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( this.query.isShowAll( ) )
        {
            addLink( EpisodeActorUri.REL_PATH_SHOW_ONLY_LINKED,
                    EpisodeActorRelTypes.GET_ALL_LINKED_ACTOR,
                    getAcceptRequestHeader( ),
                    this.primaryId );
        }
        else
        {
            addLink( EpisodeActorUri.REL_PATH_SHOW_ALL,
                    EpisodeActorRelTypes.GET_ALL_ACTOR,
                    getAcceptRequestHeader( ),
                    this.primaryId );
        }
    }

    public static class AllActor extends AbstractRelationQuery<Actor>
    {
        private final EpisodeActorDao storage;

        public AllActor( final long primaryId, final boolean showAll )
        {
            super( primaryId, showAll );
            this.storage = DaoFactory.getInstance( ).getEpisodeActorDao( );
        }

        @Override protected CollectionModelResult<Actor> doExecuteQuery( ) throws DatabaseException
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

    public static class FilterActorsByLastname extends AbstractRelationQuery<Actor>
    {
        private final EpisodeActorDao storage;
        private final String lastname;

        public FilterActorsByLastname( final long primaryId, final boolean showAll, final String lastname )
        {
            super( primaryId, showAll );
            this.lastname = lastname;
            this.storage = DaoFactory.getInstance( ).getEpisodeActorDao( );
        }

        @Override protected CollectionModelResult<Actor> doExecuteQuery( ) throws DatabaseException
        {
            if ( showAll )
            {
                return this.storage.readAllByPredicate( this.primaryId, byLastname( ) );
            }
            else
            {
                return this.storage.readByPredicate( this.primaryId, byLastname( ) );
            }
        }

        protected Predicate<Actor> byLastname( )
        {
            return l ->  StringUtils.isEmpty(lastname) || l.getLastname( ).equalsIgnoreCase( lastname );
        }
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Actor>
    {
        @Override public AbstractState build( )
        {
            return new GetAllActorOfEpisode( this );
        }
    }
}

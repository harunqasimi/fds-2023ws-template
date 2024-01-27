package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;
import java.util.function.Predicate;

public class GetAllActor extends AbstractGetCollectionState<Actor>
{
    public GetAllActor(final GetAllActor.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    protected void defineHttpResponseBody() {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity(new GenericEntity<Collection<Actor>>(this.result.getResult())
        {
        } );
    }

    @Override protected void defineTransitionLinks() {
        addLink(ActorUri.REL_PATH, ActorRelTypes.CREATE_ACTOR, getAcceptRequestHeader( ) );
    }

    public static class AllActor extends AbstractQuery<Actor>
    {
        @Override protected CollectionModelResult<Actor> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getActorDao( ).readByPredicate( all( ) );
        }
    }

    public static class ByFirstnameAndLastname extends AbstractQuery<Actor> {
        protected String firstname;

        protected String lastname;

        public ByFirstnameAndLastname(final String firstname, final String lastname ) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        @Override protected CollectionModelResult<Actor> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getActorDao( ).readByPredicate( byFirstnameAndLastname( ) );
        }

        protected Predicate<Actor> byFirstnameAndLastname( )
        {
            return p -> matchFirstname( p ) && matchLastname( p );
        }

        private boolean matchFirstname(final Actor actor )
        {
            return StringUtils.isEmpty( firstname ) || actor.getFirstname( ).equals( firstname );
        }

        private boolean matchLastname(final Actor actor )
        {
            return StringUtils.isEmpty( lastname ) || actor.getLastname( ).equals( lastname );
        }
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Actor>
    {
        @Override
        public AbstractState build( )
        {
            return new GetAllActor( this );
        }
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
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

public class GetAllSeries extends AbstractGetCollectionState<Series>
{
    public GetAllSeries(final Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    protected void defineHttpResponseBody() {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity(new GenericEntity<Collection<Series>>(this.result.getResult())
        {
        } );
    }

    @Override protected void defineTransitionLinks() {
        addLink(SeriesUri.REL_PATH, SeriesRelTypes.CREATE_SERIES, getAcceptRequestHeader( ) );
    }

    public static class AllSeries extends AbstractQuery<Series>
    {
        @Override protected CollectionModelResult<Series> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getSeriesDao( ).readByPredicate( all( ) );
        }
    }

    public static class ByNameAndGenre extends AbstractQuery<Series>
    {
        protected String name;

        protected String genre;

        public ByNameAndGenre( final String name, final String genre )
        {
            this.name = name;
            this.genre = genre;
        }

        @Override protected CollectionModelResult<Series> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getSeriesDao( ).readByPredicate( byNameAndGenre( ) );
        }

        protected Predicate<Series> byNameAndGenre( )
        {
            return p -> matchName( p ) && matchGenre( p );
        }

        private boolean matchName(final Series series )
        {
            return StringUtils.isEmpty( name ) || series.getName( ).equals( name );
        }

        private boolean matchGenre(final Series series )
        {
            return StringUtils.isEmpty( genre ) || series.getGenre( ).equals( genre );
        }
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Series>
    {
        @Override
        public AbstractState build( )
        {
            return new GetAllSeries( this );
        }
    }
}

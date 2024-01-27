package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
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

public class GetAllEpisode extends AbstractGetCollectionState<Episode>
{
    public GetAllEpisode(final GetAllEpisode.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    protected void defineHttpResponseBody() {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity(new GenericEntity<Collection<Episode>>(this.result.getResult())
        {
        } );
    }

    @Override protected void defineTransitionLinks() {
        addLink(EpisodeUri.REL_PATH, EpisodeRelTypes.CREATE_EPISODE, getAcceptRequestHeader( ) );
    }

    public static class AllEpisode extends AbstractQuery<Episode> {
        @Override protected CollectionModelResult<Episode> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance().getEpisodeDao().readByPredicate(all());
        }
    }

    public static class ByNumberAndTitle extends AbstractQuery<Episode>
    {
        protected int number;

        protected String title;

        public ByNumberAndTitle(final int number, final String title )
        {
            this.number = number;
            this.title = title;
        }

        @Override
        protected CollectionModelResult<Episode> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getEpisodeDao( ).readByPredicate( byNumberAndTitle( ) );
        }

        protected Predicate<Episode> byNumberAndTitle( )
        {
            return p -> matchNumber(p) && matchTitle(p);
        }

        private boolean matchNumber(final Episode episode )
        {
            return number == 0 || episode.getNumber( ) ==  number ;
        }

        private boolean matchTitle(final Episode episode )
        {
            return StringUtils.isEmpty( title ) || episode.getTitle( ).equals( title );
        }
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Episode>
    {
        @Override
        public AbstractState build( )
        {
            return new GetAllEpisode( this );
        }
    }
}

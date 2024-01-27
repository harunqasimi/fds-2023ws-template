package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season;


import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
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

public class GetAllSeason extends AbstractGetCollectionState<Season>
{
    public GetAllSeason(final GetAllSeason.Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    protected void defineHttpResponseBody() {
        this.responseBuilder
                .cacheControl(CachingUtils.create60SecondsPublicCaching())
                .header("Vary", "Accept")
                .entity(new GenericEntity<Collection<Season>>(this.result.getResult())
        {
        } );
    }

    @Override protected void defineTransitionLinks() {
        addLink(SeasonUri.REL_PATH, SeasonRelTypes.CREATE_SEASON, getAcceptRequestHeader( ) );
    }

    public static class AllSeason extends AbstractQuery<Season>
    {
        @Override protected CollectionModelResult<Season> doExecuteQuery( ) throws DatabaseException
        {
            return DaoFactory.getInstance( ).getSeasonDao( ).readByPredicate( all( ) );
        }
    }

    public static class ByNumberAndYearOfBroadcasting extends AbstractQuery<Season>
    {
        protected int number;

        protected int yearOfBroadcasting;

        public ByNumberAndYearOfBroadcasting(final int number, final int yearOfBroadcasting)
        {
            this.number = number;
            this.yearOfBroadcasting = yearOfBroadcasting;
        }

        @Override protected CollectionModelResult<Season> doExecuteQuery() throws DatabaseException
        {
            return DaoFactory.getInstance().getSeasonDao().readByPredicate(byNumberAndYearOfBroadcasting());
        }

        protected Predicate<Season> byNumberAndYearOfBroadcasting( )
        {
            return p -> matchNumber( p ) && matchYearOfBroadcasting( p );
        }

        private boolean matchNumber(final Season season)
        {
            return number == 0 || season.getNumber() == number;
        }

        private boolean matchYearOfBroadcasting(final Season season )
        {
            return yearOfBroadcasting == 0 || season.getYearOfBroadcasting( ) ==  yearOfBroadcasting ;
        }
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Season>
    {
        @Override
        public AbstractState build( )
        {
            return new GetAllSeason( this );
        }
    }
}

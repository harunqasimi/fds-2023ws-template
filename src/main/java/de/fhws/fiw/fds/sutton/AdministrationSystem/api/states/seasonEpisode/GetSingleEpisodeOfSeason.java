package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetSingleEpisodeOfSeason extends AbstractGetRelationState<Episode>
{
    public GetSingleEpisodeOfSeason( final GetSingleEpisodeOfSeason.Builder builder )
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
    protected SingleModelResult<Episode> loadModel( )
    {
        return DaoFactory.getInstance( ).getEpisodeDao( ).readById( this.requestedId );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink(SeasonEpisodeUri.REL_PATH_SHOW_ONLY_LINKED,
                SeasonEpisodeRelTypes.GET_ALL_LINKED_EPISODE,
                getAcceptRequestHeader( ),
                this.primaryId );

        if ( isSeasonLinkedToThisEpisode( ) )
        {
            addLink( SeasonEpisodeUri.REL_PATH_ID,
                    SeasonEpisodeRelTypes.UPDATE_SINGLE_EPISODE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );

            addLink( SeasonEpisodeUri.REL_PATH_ID,
                    SeasonEpisodeRelTypes.DELETE_LINK_FROM_SEASON_TO_EPISODE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
        else
        {
            addLink( SeasonEpisodeUri.REL_PATH_ID,
                    SeasonEpisodeRelTypes.CREATE_LINK_FROM_SEASON_TO_EPISODE,
                    getAcceptRequestHeader( ),
                    this.primaryId, this.requestedId );
        }
    }

    private boolean isSeasonLinkedToThisEpisode( )
    {
        return !DaoFactory.getInstance( )
                .getSeasonEpisodeDao( )
                .readById( this.primaryId, this.requestedId )
                .isEmpty( );
    }

    public static class Builder extends AbstractGetRelationState.AbstractGetRelationStateBuilder
    {
        @Override
        public AbstractState build( )
        {
            return new GetSingleEpisodeOfSeason( this );
        }
    }
}

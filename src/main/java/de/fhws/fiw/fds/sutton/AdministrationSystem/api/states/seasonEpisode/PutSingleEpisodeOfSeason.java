package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutSingleEpisodeOfSeason extends AbstractPutRelationState<Episode>
{
    public PutSingleEpisodeOfSeason( final PutSingleEpisodeOfSeason.Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Episode> loadModel( )
    {
        return DaoFactory.getInstance( ).getEpisodeDao( ).readById( this.requestedId );
    }

    @Override protected NoContentResult updateModel( )
    {
        return DaoFactory.getInstance( ).getSeasonEpisodeDao( ).update( this.primaryId, this.modelToUpdate );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( SeasonEpisodeUri.REL_PATH_ID,
                de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeRelTypes.GET_SINGLE_EPISODE,
                getAcceptRequestHeader( ),
                this.primaryId, this.requestedId );
    }

    public static class Builder extends AbstractPutRelationStateBuilder<Episode>
    {
        @Override public AbstractState build( )
        {
            return new PutSingleEpisodeOfSeason( this );
        }
    }
}

package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeUri;
import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.SeasonEpisodeRelTypes;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteSingleEpisodeOfSeason extends AbstractDeleteRelationState<Episode>
{
    public DeleteSingleEpisodeOfSeason( final DeleteSingleEpisodeOfSeason.Builder builder )
    {
        super( builder );
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override protected SingleModelResult<Episode> loadModel( )
    {
        return DaoFactory.getInstance( ).getSeasonEpisodeDao( ).readById( this.primaryId, this.modelIdToDelete );
    }

    @Override protected NoContentResult deleteModel( )
    {
        return DaoFactory.getInstance( ).getSeasonEpisodeDao( ).deleteRelation( this.primaryId, this.modelIdToDelete );
    }

    @Override protected void defineTransitionLinks( )
    {
        addLink( SeasonEpisodeUri.REL_PATH,
                SeasonEpisodeRelTypes.GET_ALL_LINKED_EPISODE,
                getAcceptRequestHeader( ),
                this.primaryId );
    }

    public static class Builder extends AbstractDeleteRelationStateBuilder
    {
        @Override public AbstractState build( )
        {
            return new DeleteSingleEpisodeOfSeason( this );
        }
    }
}

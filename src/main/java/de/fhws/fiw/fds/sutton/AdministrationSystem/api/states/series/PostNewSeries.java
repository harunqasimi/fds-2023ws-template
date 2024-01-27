package de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series;

import de.fhws.fiw.fds.sutton.AdministrationSystem.database.DaoFactory;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostNewSeries extends AbstractPostState<Series>
{
    public PostNewSeries(final Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {
    }

    @Override
    protected NoContentResult saveModel( )
    {
        return DaoFactory.getInstance().getSeriesDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
    }

    public static class Builder extends AbstractPostStateBuilder<Series> {
        @Override
        public AbstractState build() {
            return new PostNewSeries(this);
        }
    }
}

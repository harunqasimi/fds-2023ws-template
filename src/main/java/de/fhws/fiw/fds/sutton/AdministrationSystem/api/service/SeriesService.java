package de.fhws.fiw.fds.sutton.AdministrationSystem.api.service;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.series.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Series;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize.*;

@Path("series")
public class SeriesService  extends AbstractService {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllSeries(
            @DefaultValue( "" ) @QueryParam( "name" ) final String name,
            @DefaultValue( "" ) @QueryParam( "genre" ) final String genre,
            @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
            @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size
    )
    {
        return new GetAllSeries.Builder( )
                .setQuery( new GetAllSeries.ByNameAndGenre( name, genre ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @GET
    @Path( "{seriesId: \\d+}" )
    @Produces({MediaType.APPLICATION_JSON})
    public Response getSingleSeries( @PathParam("seriesId") final long seriesId )
    {
        return new GetSingleSeries.Builder()
                .setRequestedId(seriesId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .build()
                .execute();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createSingleSeries( final Series seriesModel )
    {
        return new PostNewSeries.Builder()
                .setModelToCreate(seriesModel)
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{seriesId: \\d+}" )
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateSingleSeries( @PathParam( "seriesId" ) final long seriesId, final Series seriesModel ) {
        return new PutSingleSeries.Builder()
                .setRequestedId(seriesId)
                .setModelToUpdate(seriesModel)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .build()
                .execute();
    }

    @DELETE
    @Path( "{seriesId: \\d+}" )
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteSingleSeries( @PathParam( "seriesId" ) final long seriesId )
    {
        return new DeleteSingleSeries.Builder( )
                .setRequestedId( seriesId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @GET
    @Path( "{seriesId: \\d+}/season" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getAllSeasonOfSeries( @PathParam( "seriesId" ) final long seriesId,
                                          @DefaultValue( "" ) @QueryParam( "number" ) final int number,
                                          @DefaultValue( "false" ) @QueryParam( "showAll" ) final boolean showAll ,
                                          @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
                                          @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size)
    {
        return new GetAllSeasonOfSeries.Builder( )
                .setParentId( seriesId )
                .setQuery( new GetAllSeasonOfSeries.FilterSeasonsByNumber( seriesId, showAll, number ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @GET
    @Path( "{seriesId: \\d+}//{seasonId: \\d+}" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getSeasonByIdOfSeries( @PathParam( "seriesId" ) final long seriesId,
                                             @PathParam( "seasonId" ) final long seasonId )
    {
        return new GetSingleSeasonOfSeries.Builder( )
                .setParentId( seriesId )
                .setRequestedId( seasonId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @POST
    @Path( "{seriesId: \\d+}/season" )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response createNewSeasonOfSeries( @PathParam( "seriesId" ) final long seriesId, final Season season )
    {
        return new PostNewSeasonOfSeries.Builder( )
                .setParentId( seriesId )
                .setModelToCreate( season )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{seriesId: \\d+}/season/{seasonId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response updateNewLocationOfPerson( @PathParam( "seriesId" ) final long seriesId,
                                               @PathParam( "seasonId" ) final long seasonId, final Season season )
    {
        return new PutSingleSeasonOfSeries.Builder( )
                .setParentId( seriesId )
                .setRequestedId( seasonId )
                .setModelToUpdate( season )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @DELETE
    @Path( "{seriesId: \\d+}/season/{seasonId: \\d+}" )
    public Response deleteSeasonOfSeries( @PathParam( "seriesId" ) final long seriesId,
                                            @PathParam( "seasonId" ) final long seasonId )
    {
        return new DeleteSingleSeasonOfSeries.Builder( )
                .setParentId( seriesId )
                .setRequestedId( seasonId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
}

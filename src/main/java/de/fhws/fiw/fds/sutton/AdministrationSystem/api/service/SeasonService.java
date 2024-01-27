package de.fhws.fiw.fds.sutton.AdministrationSystem.api.service;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.season.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seriesSeason.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Season;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize.*;

public class SeasonService extends AbstractService {
    @GET
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getAllSeason(
            @DefaultValue( "" ) @QueryParam( "number" ) final int number,
            @DefaultValue( "" ) @QueryParam( "yearOfBroadcasting" ) final int yearOfBroadcasting,
            @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
            @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size
    )
    {
        return new GetAllSeason.Builder( )
                .setQuery( new GetAllSeason.ByNumberAndYearOfBroadcasting( number, yearOfBroadcasting ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @GET
    @Path( "{seasonId: \\d+}" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getSingleSeason( @PathParam( "seasonId" ) final long seasonId )
    {
        return new GetSingleSeason.Builder( )
                .setRequestedId( seasonId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @POST
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response createSingleSeason( final Season seasonModel )
    {
        return new PostNewSeason.Builder( )
                .setModelToCreate( seasonModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{seasonId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response updateSingleSeason( @PathParam( "seasonId" ) final long seasonId, final Season seasonModel )
    {
        return new PutSingleSeason.Builder( )
                .setRequestedId( seasonId )
                .setModelToUpdate( seasonModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @DELETE
    @Path( "{seasonId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response deleteSingleSeason( @PathParam( "seasonId" ) final long seasonId )
    {
        return new DeleteSingleSeason.Builder( )
                .setRequestedId( seasonId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @GET
    @Path( "{seasonId: \\d+}/episode" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getAllEpisodeOfSeason( @PathParam( "seasonId" ) final long seasonId,
                                       @DefaultValue( "" ) @QueryParam( "number" ) final int number,
                                       @DefaultValue( "false" ) @QueryParam( "showAll" ) final boolean showAll,
                                           @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
                                           @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size)
    {
        return new GetAllEpisodeOfSeason.Builder( )
                .setParentId( seasonId )
                .setQuery( new GetAllEpisodeOfSeason.FilterEpisodesByNumber( seasonId, showAll, number ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @GET
    @Path( "{seasonId: \\d+}//{episodeId: \\d+}" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getEpisodeByIdOfSeason( @PathParam( "seasonId" ) final long seasonId,
                                             @PathParam( "episodeId" ) final long episodeId )
    {
        return new GetSingleEpisodeOfSeason.Builder( )
                .setParentId( seasonId )
                .setRequestedId( episodeId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @POST
    @Path( "{seasonId: \\d+}/episode" )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response createNewEpisodeOfSeason( @PathParam( "seasonId" ) final long seasonId, final Episode episode )
    {
        return new PostNewEpisodeOfSeason.Builder( )
                .setParentId( seasonId )
                .setModelToCreate( episode )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{seasonId: \\d+}/episode/{episodeId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response updateNewEpisodeOfSeason( @PathParam( "seasonId" ) final long seasonId,
                                               @PathParam( "episodeId" ) final long episodeId, final Episode episode )
    {
        return new PutSingleEpisodeOfSeason.Builder( )
                .setParentId( seasonId )
                .setRequestedId( episodeId )
                .setModelToUpdate( episode)
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @DELETE
    @Path( "{seasonId: \\d+}/episode/{episodeId: \\d+}" )
    public Response deleteEpisodeOfSeason( @PathParam( "seasonId" ) final long seasonId,
                                          @PathParam( "episodeId" ) final long episodeId )
    {
        return new DeleteSingleEpisodeOfSeason.Builder( )
                .setParentId( seasonId )
                .setRequestedId( episodeId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
}

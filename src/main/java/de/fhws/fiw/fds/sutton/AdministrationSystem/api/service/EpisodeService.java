package de.fhws.fiw.fds.sutton.AdministrationSystem.api.service;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episode.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.episodeActor.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.seasonEpisode.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Episode;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize.*;

public class EpisodeService extends AbstractService
{
    @GET
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getAllEpisode(
            @DefaultValue( "" ) @QueryParam( "number" ) final int number,
            @DefaultValue( "" ) @QueryParam( "title" ) final String title,
            @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
            @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size
    )
    {
        return new GetAllEpisode.Builder( )
                .setQuery( new GetAllEpisode.ByNumberAndTitle( number, title ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @GET
    @Path("{episodeId: \\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getSingleEpisode(@PathParam("episodeId") final long episodeId) {
        return new GetSingleEpisode.Builder( )
                .setRequestedId( episodeId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @POST
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response createSingleEpisode( final Episode episodeModel )
    {
        return new PostNewEpisode.Builder( )
                .setModelToCreate( episodeModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{episodeId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response updateSingleEpisode( @PathParam( "episodeId" ) final long episodeId, final Episode episodeModel )
    {
        return new PutSingleEpisode.Builder( )
                .setRequestedId( episodeId )
                .setModelToUpdate( episodeModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @DELETE
    @Path( "{episodeId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response deleteSingleEpisode( @PathParam( "episodeId" ) final long episodeId )
    {
        return new DeleteSingleEpisode.Builder( )
                .setRequestedId( episodeId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @GET
    @Path( "{episodeId: \\d+}/actor" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getAllActorOfEpisode( @PathParam( "episodeId" ) final long episodeId,
                                        @DefaultValue( "" ) @QueryParam( "lastname" ) final String lastname,
                                        @DefaultValue( "false" ) @QueryParam( "showAll" ) final boolean showAll ,
                                          @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
                                          @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size)


    {
        return new GetAllActorOfEpisode.Builder( )
                .setParentId( episodeId )
                .setQuery( new GetAllActorOfEpisode.FilterActorsByLastname( episodeId, showAll, lastname ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @GET
    @Path( "{episodeId: \\d+}//{actorId: \\d+}" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getActorByIdOfEpisode( @PathParam( "episodeId" ) final long episodeId,
                                            @PathParam( "actorId" ) final long actorId )
    {
        return new GetSingleActorOfEpisode.Builder( )
                .setParentId( episodeId )
                .setRequestedId( actorId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @POST
    @Path( "{episodeId: \\d+}/actor" )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response createNewActorOfEpisode( @PathParam( "episodeId" ) final long episodeId, final Actor actor )
    {
        return new PostNewActorOfEpisode.Builder( )
                .setParentId( episodeId )
                .setModelToCreate( actor )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{episodeId: \\d+}/actor/{actorId: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON } )
    public Response updateNewActorOfEpisode( @PathParam( "episodeId" ) final long episodeId,
                                              @PathParam( "actorId" ) final long actorId, final Actor actor)
    {
        return new PutSingleActorOfEpisode.Builder( )
                .setParentId( episodeId )
                .setRequestedId( actorId )
                .setModelToUpdate( actor)
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
    @DELETE
    @Path( "{episodeId: \\d+}/actor/{actorId: \\d+}" )
    public Response deleteActorOfEpisode( @PathParam( "episodeId" ) final long episodeId,
                                           @PathParam( "actorId" ) final long actorId )
    {
        return new DeleteSingleActorOfEpisode.Builder( )
                .setParentId( episodeId )
                .setRequestedId( actorId )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
}

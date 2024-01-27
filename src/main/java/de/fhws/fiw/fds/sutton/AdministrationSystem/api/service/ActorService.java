package de.fhws.fiw.fds.sutton.AdministrationSystem.api.service;

import de.fhws.fiw.fds.sutton.AdministrationSystem.api.states.actors.*;
import de.fhws.fiw.fds.sutton.AdministrationSystem.models.Actor;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize.*;

public class ActorService extends AbstractService {
    @GET
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getAllActor(
            @DefaultValue( "" ) @QueryParam( "firstname" ) final String firstName,
            @DefaultValue( "" ) @QueryParam( "lastname" ) final String lastName,
            @DefaultValue( "0" ) @QueryParam(QUERY_PARAM_OFFSET) final int offset,
            @DefaultValue(DEFAULT_PAGE_SIZE_STR) @QueryParam(QUERY_PARAM_SIZE) final int size
    )
    {
        return new GetAllActor.Builder( )
                .setQuery( new GetAllActor.ByFirstnameAndLastname( firstName, lastName ).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)) )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @GET
    @Path( "{id: \\d+}" )
    @Produces( { MediaType.APPLICATION_JSON} )
    public Response getSingleActor( @PathParam( "id" ) final long id )
    {
        return new GetSingleActor.Builder( )
                .setRequestedId( id )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @POST
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response createSingleActor( final Actor actorModel )
    {
        return new PostNewActor.Builder( )
                .setModelToCreate( actorModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @PUT
    @Path( "{id: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response updateSingleActor( @PathParam( "id" ) final long id, final Actor actorModel )
    {
        return new PutSingleActor.Builder( )
                .setRequestedId( id )
                .setModelToUpdate( actorModel )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }

    @DELETE
    @Path( "{id: \\d+}" )
    @Consumes( { MediaType.APPLICATION_JSON} )
    public Response deleteSingleActor( @PathParam( "id" ) final long id )
    {
        return new DeleteSingleActor.Builder( )
                .setRequestedId( id )
                .setUriInfo( this.uriInfo )
                .setRequest( this.request )
                .setHttpServletRequest( this.httpServletRequest )
                .setContext( this.context )
                .build( )
                .execute( );
    }
}

package fca.suayed.resources;

import fca.suayed.dal.StoreDal;
import fca.suayed.dto.ClientDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/clients")
public class ClientsResource {

    @Inject
    StoreDal storeDal;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all clients")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "List of clients retrieved successfully",
            content = @Content(mediaType = "application/json")
        )
    })
    public Response getClients() {
        var responseDto = storeDal.getClients();
        return Response.ok(responseDto).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new client")
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Client created successfully",
            content = @Content(mediaType = "application/json")
        )
    })
    public Response addClient(ClientDto clientDto) {
        var responseDto = storeDal.addClient(clientDto);
        return Response.ok(responseDto).build();
    }
}
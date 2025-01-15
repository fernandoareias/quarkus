package com.fernando.controllers;

import com.fernando.entities.Restaurante;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteController {

    @GET
    public List<Restaurante> hello(){
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    public void adicionar(Restaurante dto){
        dto.persist();
        Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, Restaurante dto){
        Restaurante restauranteOp = Restaurante.findById(id);

        restauranteOp.nome = dto.nome;

        restauranteOp.persist();

    }


    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id){
        var restauranteOp = Restaurante.findByIdOptional(id);

        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {throw new NotFoundException();});
    }

}

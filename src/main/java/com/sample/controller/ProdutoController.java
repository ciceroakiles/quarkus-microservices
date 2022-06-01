package com.sample.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.sample.entity.Produto;
import com.sample.service.ProdutoService;

@Path("/api/v1/produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {
    
    @Inject
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @POST
    @Transactional
    public Response criaProd(Produto produto) throws IOException {
        produtoService.index(produto);
        return Response.status(Status.CREATED).entity(produto).build();
    }

    @GET
    @Path("/{id}")
    public Produto findById(@PathParam("id") Long id) throws IOException {
        return produtoService.get(id.toString());
    }
}

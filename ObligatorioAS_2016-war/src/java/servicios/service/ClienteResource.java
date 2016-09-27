/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.service;

import Entidades.ClienteEntity;
import com.google.gson.Gson;
import Dominio.ClienteBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author alvaro
 */
@Path("usuario")
public class ClienteResource {
    
    @EJB
    private ClienteBean clienteBean;
    
    @Context
    private UriInfo context;

    public ClienteResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<ClienteEntity> list = clienteBean.listar();
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(String body) {
        Gson gson = new Gson();
        ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        Response r;
        ClienteEntity creado = clienteBean.agregar(u);
        if (creado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("afadfadf")
                    .build();
        } else {
            r = Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(creado))
                    .build();
        }
        return r;
    } 
    
}

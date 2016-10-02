/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.service;

import ObligatorioAS_2016.envio.EnvioBean;
import ObligatorioAS_2016.entidades.CadeteEntity;
import ObligatorioAS_2016.entidades.EnvioEntity;
import com.google.gson.Gson;
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
 * @author Gonzalo
 */
@Path("envio")
public class EnvioResource {
    @EJB
    private EnvioBean envioBean;
    
     @Context
    private UriInfo context;

    public EnvioResource() {
    }

    @GET
    @Path("getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<EnvioEntity> list = envioBean.listar();
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @POST
    @Path("agregarEnvio")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(String body) {
        Gson gson = new Gson();
        EnvioEntity u = gson.fromJson(body, EnvioEntity.class);
        Response r;
        EnvioEntity creado = envioBean.agregar(u);
        if (creado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Envio")
                    .build();
        } else {
            r = Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(creado))
                    .build();
        }
        return r;
    } 
    @POST
    @Path("modificarEnvio")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(String body) {
        Gson gson = new Gson();
        EnvioEntity u = gson.fromJson(body, EnvioEntity.class);
        Response r;
        EnvioEntity modificado = envioBean.modificar(u);
        if (modificado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Envio")
                    .build();
        } else {
            r = Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(modificado))
                    .build();
        }
        return r;
    } 
     @POST
    @Path("eliminarEnvio")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(String body) {
        Gson gson = new Gson();
        EnvioEntity u = gson.fromJson(body, EnvioEntity.class);
        Response r;
        Boolean modificado = envioBean.eliminar(u);
        if (modificado == false) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Envio")
                    .build();
        } else {
            r = Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(modificado))
                    .build();
        }
        return r;
    } 
}

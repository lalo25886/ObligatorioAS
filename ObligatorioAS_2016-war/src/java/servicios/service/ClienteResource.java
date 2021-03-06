package servicios.service;

import ObligatorioAS_2016.cliente.ClienteBean;
import ObligatorioAS_2016.entidades.ClienteEntity;
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
 * @author alvaro
 */

@Path("cliente")
public class ClienteResource {

    @EJB
    private ClienteBean clienteBean;

    @Context
    private UriInfo context;

    public ClienteResource() {
    }

    @GET
    @Path("getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<ClienteEntity> list = clienteBean.listar();
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @POST
    @Path("agregarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(String body) {
        Gson gson = new Gson();
        ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        Response r;
        ClienteEntity creado = clienteBean.agregar(u);
        if (creado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Cliente")
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
    @Path("modificarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(String body) {
        Gson gson = new Gson();
        ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        Response r;
        ClienteEntity modificado = clienteBean.modificar(u);
        if (modificado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Cliente")
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
    @Path("eliminarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(String body) {
        Gson gson = new Gson();
        ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        Response r;
        Boolean modificado = clienteBean.eliminar(u);
        if (!modificado) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("afadfadf")
                    .build();
        } else {
            r = Response
                    .status(Response.Status.CREATED)
                    .entity(gson.toJson(modificado))
                    .build();
        }
        return r;
    }

   @GET
   @Path("getClientesEnvios")
   @Consumes(MediaType.APPLICATION_JSON)
   public String  getClientesEnvios() {
       Gson gson = new Gson();
       List<ClienteEntity> list = clienteBean.listarClientesEnvios();
       return gson.toJson(list);
   }
}

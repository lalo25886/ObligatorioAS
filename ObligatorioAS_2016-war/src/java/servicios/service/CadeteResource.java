
package servicios.service;

import ObligatorioAS_2016.cadete.CadeteBean;
import ObligatorioAS_2016.entidades.CadeteEntity;

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
@Path("cadete")
public class CadeteResource {
    @EJB
    private CadeteBean cadeteBean;

    @Context
    private UriInfo context;

    public CadeteResource() {
    }

    @GET
    @Path("getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<CadeteEntity> list = cadeteBean.listar();
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @POST
    @Path("agregarCadete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(String body) {
        Gson gson = new Gson();
        CadeteEntity u = gson.fromJson(body, CadeteEntity.class);
        Response r;
        CadeteEntity creado = cadeteBean.agregar(u);
        if (creado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Cadete")
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
    @Path("modificarCadete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(String body) {
        Gson gson = new Gson();
        CadeteEntity u = gson.fromJson(body, CadeteEntity.class);
        Response r;
        CadeteEntity modificado = cadeteBean.modificar(u);
        if (modificado == null) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Cadete")
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
    @Path("eliminarCadete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(String body) {
        Gson gson = new Gson();
        CadeteEntity u = gson.fromJson(body, CadeteEntity.class);
        Response r;
        Boolean modificado = cadeteBean.eliminar(u);
        if (!modificado) {
            r = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Cadete")
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

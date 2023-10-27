package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

@Path("/json")
public class JsonResource {

    private static final ObjectMapper mapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello()
    {
        ObjectNode json = mapper.createObjectNode();
        json.put("result","Jersey JSON example using Jackson ");
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User hello(@PathParam("name") String name)
    {
        return new User(1,name);
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> helloList() {
        return Arrays.asList(
                new User(1, "mkyong"),
                new User(2, "zilap")
        );
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {

        ObjectNode json = mapper.createObjectNode();
        System.out.println(user);
        json.put("status", "ok");
        return Response.status(Response.Status.CREATED).entity(json).build();

    }
}

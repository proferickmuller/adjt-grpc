package br.dev.erm;

import br.dev.erm.biblioteca.Biblioteca;
import br.dev.erm.biblioteca.BibliotecaProto;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @GrpcClient
    Biblioteca biblioteca;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/{name}")
    public Uni<String> hello(String name) {
        return biblioteca.sayHello(
                BibliotecaProto.HelloRequest.newBuilder().setName(name).build()
        ).onItem().transform(helloReply -> helloReply.getMessage());
    }

}

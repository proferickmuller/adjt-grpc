package br.dev.erm;

import br.dev.erm.biblioteca.Biblioteca;
import br.dev.erm.biblioteca.BibliotecaGrpc;
import br.dev.erm.biblioteca.LivrosOuterClass;
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
                LivrosOuterClass.HelloRequest.newBuilder().setName("Erick").build()
        ).onItem().transform(LivrosOuterClass.HelloReply::getMessage);
    }

}

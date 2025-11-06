package br.dev.erm.BibliotecaCliente.resources;

import br.dev.erm.BibliotecaCliente.entities.LivroEntity;
import br.dev.erm.BibliotecaCliente.resources.models.NovoLivroRequest;
import br.dev.erm.biblioteca.Biblioteca;
import br.dev.erm.biblioteca.BibliotecaProto;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;


@Path("/livrosClient/v1/")
public class ConsumerResource {

    @GrpcClient
    Biblioteca biblioteca;

    @GET
    @Path("/livro/{id}")
    public Uni<LivroEntity> livroById(long id) {

        var livroId = BibliotecaProto.LivroId.newBuilder().setId(id).build();
        var livro = biblioteca.obterLivroPorId(livroId).onItem()
                .transform(x -> { return new LivroEntity(x.getId(), x.getAutor(), x.getTitulo(), x.getAnoPublicacao()); });
        return livro;

    }

    @GET
    @Path("/livro")
    public Uni<List<LivroEntity>> todosLivros() {

        var livros = biblioteca.listarLivros(com.google.protobuf.Empty.newBuilder().build())
                .onItem().transformToMulti(x -> io.smallrye.mutiny.Multi.createFrom().iterable(x.getLivrosList()))
                .onItem().transform(x -> { return new LivroEntity(x.getId(), x.getAutor(), x.getTitulo(), x.getAnoPublicacao()); })
                .collect().asList();
        return livros;

    }

    @POST
    @Path("/livro")
    public Uni<LivroEntity> adicionarLivro(NovoLivroRequest livroRequest) {

        var novoLivro = BibliotecaProto.NovoLivro.newBuilder()
                .setAutor(livroRequest.autor())
                .setTitulo(livroRequest.titulo())
                .setAnoPublicacao(livroRequest.anoPublicacao())
                .build();

        var livroAdicionado = biblioteca.adicionarLivro(novoLivro)
                .onItem().transform(x -> { return new LivroEntity(x.getId(), x.getAutor(), x.getTitulo(), x.getAnoPublicacao()); });

        return livroAdicionado;

    }


}


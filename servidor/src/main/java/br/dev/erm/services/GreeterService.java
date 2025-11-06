package br.dev.erm.services;


import br.dev.erm.biblioteca.Biblioteca;
import br.dev.erm.biblioteca.BibliotecaProto;
import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class GreeterService implements Biblioteca {

    @Override
    public Uni<BibliotecaProto.Livro> adicionarLivro(BibliotecaProto.NovoLivro request) {
        return null;
    }

    @Override
    public Uni<BibliotecaProto.Livros> listarLivros(Empty request) {
        return null;
    }

    @Override
    public Uni<BibliotecaProto.Livro> obterLivroPorId(BibliotecaProto.LivroId request) {
        return null;
    }

    @Override
    public Uni<BibliotecaProto.HelloReply> sayHello(BibliotecaProto.HelloRequest request) {

        return Uni.createFrom().item(() ->
                BibliotecaProto.HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());

    }
}

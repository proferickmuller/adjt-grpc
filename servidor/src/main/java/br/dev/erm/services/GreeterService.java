package br.dev.erm.services;

import br.dev.erm.biblioteca.Biblioteca;
import br.dev.erm.biblioteca.LivrosOuterClass;
import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class GreeterService implements Biblioteca {

    @Override
    public Uni<LivrosOuterClass.Livro> adicionarLivro(LivrosOuterClass.NovoLivro request) {
        return null;
    }

    @Override
    public Uni<LivrosOuterClass.Livros> listarLivros(Empty request) {
        return null;
    }

    @Override
    public Uni<LivrosOuterClass.Livro> obterLivroPorId(LivrosOuterClass.LivroId request) {
        return null;
    }

    @Override
    public Uni<LivrosOuterClass.HelloReply> sayHello(LivrosOuterClass.HelloRequest request) {
        return Uni.createFrom().item(
                () -> LivrosOuterClass.HelloReply.newBuilder().setMessage("Hello " + request.getName() + "!").build()
        );
    }

}

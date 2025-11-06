package br.dev.erm.services;


import br.dev.erm.biblioteca.Biblioteca;
import br.dev.erm.biblioteca.BibliotecaProto;
import br.dev.erm.data.LivroEntity;
import br.dev.erm.data.LivroRepository;
import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class BibliotecaService implements Biblioteca {

    @Inject
    LivroRepository livroRepository;

    @Override
    @Blocking
    @Transactional
    public Uni<BibliotecaProto.Livro> adicionarLivro(BibliotecaProto.NovoLivro request) {
        LivroEntity le = new LivroEntity();
        le.setAnoPublicacao(request.getAnoPublicacao());
        le.setAutor(request.getAutor());
        le.setTitulo(request.getTitulo());

        livroRepository.persist(le);

        return Uni.createFrom().item(() ->
                BibliotecaProto.Livro.newBuilder()
                        .setTitulo(le.getTitulo())
                        .setAutor(le.getTitulo())
                        .setAnoPublicacao(le.getAnoPublicacao())
                        .setId(le.getId())
                        .build()
        );
    }

    @Override
    @Blocking
    public Uni<BibliotecaProto.Livros> listarLivros(Empty request) {

        var livros = livroRepository.findAll();
        List<BibliotecaProto.Livro> livrosProto = livros.stream().map((x) -> {
            return BibliotecaProto.Livro.newBuilder()
                    .setAnoPublicacao(x.getAnoPublicacao())
                    .setAutor(x.getAutor())
                    .setTitulo(x.getTitulo())
                    .setId(x.getId()).build();
        }).toList();

        return Uni.createFrom().item(() -> {
            BibliotecaProto.Livros.Builder builder = BibliotecaProto.Livros.newBuilder();
            builder.addAllLivros(livrosProto);
            return builder.build();
        });
    }


    @Override
    @Transactional
    public Uni<BibliotecaProto.Livro> obterLivroPorId(BibliotecaProto.LivroId request) {
        Long livroId = request.getId();
        LivroEntity livroEntity = livroRepository.findById(livroId);

        if (livroEntity == null) {
            return Uni.createFrom().item(() -> BibliotecaProto.Livro.newBuilder().build());
        }
        return Uni.createFrom().item(
                () -> BibliotecaProto.Livro.newBuilder()
                        .setAnoPublicacao(livroEntity.getAnoPublicacao())
                        .setAutor(livroEntity.getAutor())
                        .setTitulo(livroEntity.getTitulo())
                        .build()
        );
    }
}

package br.dev.erm.BibliotecaCliente.entities;

public record LivroEntity(
        Long id,
        String autor,
        String titulo,
        Integer anoPublicacao
) {
}

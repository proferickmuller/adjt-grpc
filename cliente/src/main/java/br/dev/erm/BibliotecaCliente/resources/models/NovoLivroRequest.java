package br.dev.erm.BibliotecaCliente.resources.models;

public record NovoLivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}

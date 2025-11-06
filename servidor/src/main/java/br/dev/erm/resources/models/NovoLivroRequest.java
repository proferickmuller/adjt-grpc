package br.dev.erm.resources.models;

public record NovoLivroRequest(
        String titulo,
        String autor,
        int anoPublicacao
) {
}

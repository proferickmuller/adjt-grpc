package br.dev.erm.resources.models;

import br.dev.erm.data.LivroEntity;

import java.util.List;

public record TodosLivrosResponse(
        List<LivroEntity> data
) {
}

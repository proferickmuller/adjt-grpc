package br.dev.erm.resources.models;

import br.dev.erm.data.LivroEntity;

public record LivroResponse(
        LivroEntity data
) {
}

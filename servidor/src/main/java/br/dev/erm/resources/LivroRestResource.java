package br.dev.erm.resources;

import br.dev.erm.data.LivroEntity;
import br.dev.erm.data.LivroRepository;
import br.dev.erm.resources.models.LivroResponse;
import br.dev.erm.resources.models.NovoLivroRequest;
import br.dev.erm.resources.models.TodosLivrosResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/v1")
public class LivroRestResource {

    private final LivroRepository livroRepository;

    public LivroRestResource(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Path("/livro")
    @POST
    @Transactional
    public LivroResponse novoLivro(NovoLivroRequest request) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setAutor(request.autor());
        livroEntity.setAnoPublicacao(request.anoPublicacao());
        livroEntity.setTitulo(request.titulo());

        livroRepository.persist(livroEntity);
        LivroResponse response = new LivroResponse(livroEntity);

        return response;
    }

    @Path("/livro")
    @GET
    public TodosLivrosResponse todosLivros() {
        var livros = livroRepository.findAll().stream().toList();
        TodosLivrosResponse response = new TodosLivrosResponse(livros);
        return response;
    }

    @Path("/livro/{id}")
    @GET
    // @Transactional
    public Response livroPorId(Long id) {
        var livro = livroRepository.findById(id);
        if (livro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LivroResponse lr = new LivroResponse(livro);
        return Response.ok().entity(lr).build();
    }
}

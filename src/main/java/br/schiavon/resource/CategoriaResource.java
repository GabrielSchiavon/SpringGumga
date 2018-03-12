package br.schiavon.resource;

import br.schiavon.dto.CategoriaDTO;
import br.schiavon.dto.conversor.CategoriaParaCategoriaDTO;
import br.schiavon.entity.Categoria;
import br.schiavon.exception.EntidadeNaoEncontrada;
import br.schiavon.service.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(
        value = CategoriaResource.URI_RESOURCE,
//        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CategoriaResource {

    protected static final String URI_RESOURCE = "/api/categorias";
    @Autowired
    private ServiceBase<Categoria, String> categoriaService;
    @Autowired
    private CategoriaParaCategoriaDTO categoriaParaCategoriaDTO;


    @RequestMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(categoriaParaCategoriaDTO.apply(categoriaService.getAll()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Categoria> optional = categoriaService.getOneById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(categoriaParaCategoriaDTO.apply(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity novoProduto(@RequestBody CategoriaDTO categoriaDTO) {
        final Categoria categoria = new Categoria(categoriaDTO.getNome());
        this.categoriaService.salvar(categoria);
        return ResponseEntity
                .created(URI.create(String.format("%s/%s", ProdutoResource.URI_RESOURCE, categoria.getId())))
                .body(categoria);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity alterar(@PathVariable String id, @RequestBody CategoriaDTO categoriaDTO) {
        Boolean resultado = this.categoriaService.alterar(id, new Categoria(categoriaDTO.getNome()));

        if(resultado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Boolean resultado = this.categoriaService.delete(id);
        if(resultado) {
            return ResponseEntity.noContent().build();
        }

        throw new EntidadeNaoEncontrada(String.format("*** ERRO *** Entidade Produto com o id %s, pois ela não existe na base de dados.", id));
    }
}

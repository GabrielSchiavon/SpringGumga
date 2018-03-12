package br.schiavon.resource;

import br.schiavon.dto.conversor.ProdutoParaProdutoDTO;
import br.schiavon.dto.ProdutoDTO;
import br.schiavon.entity.Produto;
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
        value = ProdutoResource.URI_RESOURCE,
//        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ProdutoResource {
    protected static final String URI_RESOURCE = "/api/produtos";

    @Autowired
    private ServiceBase<Produto, String> produtoService;
    @Autowired
    private ProdutoParaProdutoDTO produtoParaProdutoDTO;



    @RequestMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(produtoParaProdutoDTO.apply(produtoService.getAll()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Optional<Produto> optional = produtoService.getOneById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(produtoParaProdutoDTO.apply(optional.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity novoProduto(@RequestBody ProdutoDTO produtoDTO) {
        final Produto produto = new Produto(produtoDTO.getNome());
        this.produtoService.salvar(produto);
        return ResponseEntity
                .created(URI.create(String.format("%s/%s", ProdutoResource.URI_RESOURCE, produto.getId())))
                .body(produto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity alterar(@PathVariable String id, @RequestBody ProdutoDTO produtoDTO) {
        Boolean resultado = this.produtoService.alterar(id, new Produto(produtoDTO.getNome()));

        if(resultado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        Boolean resultado = this.produtoService.delete(id);
        if(resultado) {
            return ResponseEntity.noContent().build();
        }

        throw new EntidadeNaoEncontrada(String.format("*** ERRO *** Entidade Produto com o id %s, pois ela não existe na base de dados.", id));
    }
}

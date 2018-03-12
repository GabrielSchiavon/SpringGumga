package br.schiavon.repository;

import br.schiavon.entity.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class ProdutoRepository implements RepositoryBase<Produto, String> {

    private List<Produto> produtos = new ArrayList<>();


    @Override
    public List<Produto> getAll() {
        return produtos;
    }

    @Override
    public Optional<Produto> getOneById(String id) {
        return this.produtos
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Produto salvar(Produto entidade) {
        produtos.add(entidade);
        return entidade;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Produto> produto = this.getOneById(id);
        if(produto.isPresent()) {
            this.produtos = this.produtos
                    .stream()
                    .filter(p -> !p.getId().equals(id))
                    .collect(toList());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean alterar(String id, Produto entidade) {
        Optional<Produto> produto = this.getOneById(id);
        if(produto.isPresent()) {
            Produto resutado = produto.get();
            resutado.setNome(entidade.getNome());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}

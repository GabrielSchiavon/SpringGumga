package br.schiavon.repository;

import br.schiavon.entity.Categoria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class CategoriaRepository implements RepositoryBase<Categoria, String> {

    private List<Categoria> categorias = new ArrayList<Categoria>();


    @Override
    public List<Categoria> getAll() {
        return categorias;
    }

    @Override
    public Optional<Categoria> getOneById(String id) {
        return this.categorias
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public Categoria salvar(Categoria entidade) {
        categorias.add(entidade);
        return entidade;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Categoria> produto = this.getOneById(id);
        if(produto.isPresent()) {
            this.categorias = this.categorias
                    .stream()
                    .filter(c -> !c.getId().equals(id))
                    .collect(toList());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean alterar(String id, Categoria entidade) {
        Optional<Categoria> produto = this.getOneById(id);
        if(produto.isPresent()) {
            Categoria resutado = produto.get();
            resutado.setNome(entidade.getNome());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}

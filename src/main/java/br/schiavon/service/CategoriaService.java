package br.schiavon.service;

import br.schiavon.entity.Categoria;
import br.schiavon.repository.RepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends ServiceBase<Categoria, String> {

    @Autowired
    public CategoriaService(@Qualifier("categoriaRepository") RepositoryBase<Categoria, String> repository) {
        super(repository);
    }
}

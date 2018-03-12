package br.schiavon.service;

import br.schiavon.entity.Produto;
import br.schiavon.repository.RepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends ServiceBase<Produto, String> {

    @Autowired
    public ProdutoService(@Qualifier("produtoRepository") RepositoryBase<Produto, String> repository) {
        super(repository);
    }

}

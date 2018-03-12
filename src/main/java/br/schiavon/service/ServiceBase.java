package br.schiavon.service;

import br.schiavon.repository.RepositoryBase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class ServiceBase<T, ID> implements OperacoesBase<T, ID>{

    private RepositoryBase<T, ID> repository;

    public ServiceBase(RepositoryBase<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T salvar(T entidade) { return this.repository.salvar(entidade); }

    @Override
    public Optional<T> getOneById(ID id) { return this.repository.getOneById(id); }

    @Override
    public Boolean delete(ID id) { return this.repository.delete(id); }

    @Override
    public Boolean alterar(ID id, T entidade) { return this.repository.alterar(id, entidade); }

    @Override
    public List<T> getAll() {return this.repository.getAll();}
}

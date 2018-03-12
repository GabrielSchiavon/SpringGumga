package br.schiavon.service;

import java.util.List;
import java.util.Optional;

public interface OperacoesBase<T, ID> {
    T salvar(T entidade);
    Optional<T> getOneById(ID id);
    Boolean delete(ID id);
    Boolean alterar(ID id, T entidade);
    List<T> getAll();
}
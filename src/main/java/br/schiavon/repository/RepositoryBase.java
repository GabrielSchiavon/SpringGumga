package br.schiavon.repository;

import br.schiavon.service.OperacoesBase;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBase<T, ID> extends OperacoesBase<T, ID> {
}

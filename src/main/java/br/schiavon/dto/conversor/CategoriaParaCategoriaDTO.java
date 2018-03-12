package br.schiavon.dto.conversor;

import br.schiavon.dto.CategoriaDTO;
import br.schiavon.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaParaCategoriaDTO implements SuperConversor<Categoria, CategoriaDTO> {

    @Override
    public CategoriaDTO apply(Categoria categoria) {
        return CategoriaDTO
                .build()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .perecivel(categoria.getPerecivel())
                .builder();
    }
}
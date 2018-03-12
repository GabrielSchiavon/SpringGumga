package br.schiavon.dto.conversor;

import br.schiavon.dto.ProdutoDTO;
import br.schiavon.entity.Produto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProdutoParaProdutoDTO implements SuperConversor<Produto, ProdutoDTO> {

    @Override
    public ProdutoDTO apply(Produto produto) {
        List<String> categoriasID = produto
                .getCategorias()
                .stream()
                .map(p -> p.getId())
                .collect(toList());
        //return new ProdutoDTO(produto.getId(), produto.getNome(), categoriasID);
//        return ProdutoDTO.build()
//                .id(produto.getId())
//                .nome(produto.getNome())
//                .addCategorias(categoriasID)
//                .builder();
        return ProdutoDTO
                .build2()
                .id(produto.getId())
                .nome(produto.getNome())
                .builder();
    }
}

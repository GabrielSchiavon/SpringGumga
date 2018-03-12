package br.schiavon.dto.conversor;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public interface SuperConversor<A, B> extends Function<A, B> {

    default List<B> apply(List<A> lista) {
        return lista.stream()
                .map(this::apply)
                .collect(toList());
    }
}

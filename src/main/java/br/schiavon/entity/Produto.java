package br.schiavon.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Produto {

    private String id;
    private String nome;
    private BigDecimal valorVenda;
    private BigDecimal valorCusto;
    private List<Categoria> categorias;

    public Produto() {
        this.categorias = new ArrayList<>();
    }

    public Produto(String nome, BigDecimal valorVenda, BigDecimal valorCusto, List<Categoria> categorias) {
        this.id = gerarID();
        this.nome = nome;
        this.valorVenda = valorVenda;
        this.valorCusto = valorCusto;
        this.categorias = categorias;
    }

    public Produto(String nome, BigDecimal valorCusto,  Integer percentualMargem, List<Categoria> categorias) {
        this(nome, valorCusto.multiply(BigDecimal.valueOf(percentualMargem / 100)).add(valorCusto), valorCusto, categorias);
    }

    public Produto(String nome) {
        this();
        this.id = gerarID();
        this.nome = nome;
    }

    private String gerarID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public static Builder build() {
        return new Builder();
    }


    public interface BuilderProdutoValorVenda {
        BuilderProdutoValorCusto valorVenda(BigDecimal valor);
    }

    public interface BuilderProdutoValorCusto {
        BuilderProduto valorCusto(BigDecimal valor);
    }

    public static class BuilderProdutoValorVendaImp implements BuilderProdutoValorVenda {
        private Builder builder;

        public BuilderProdutoValorVendaImp(Builder builder) {
            this.builder = builder;
        }

        @Override
        public BuilderProdutoValorCusto valorVenda(BigDecimal valor) {
            builder.valorVenda = valor;
            return new BuilderProdutoValorCustoImp(builder);
        }
    }

    public static class BuilderProdutoValorCustoImp implements BuilderProdutoValorCusto {
        private Builder builder;

        public BuilderProdutoValorCustoImp(Builder builder) {
            this.builder = builder;
        }

        @Override
        public BuilderProduto valorCusto(BigDecimal valor) {
            builder.valorCusto = valor;
            return new BuilderProduto(builder);
        }
    }

    public static class BuilderProduto {
        private Builder builder;
        private List<Categoria> categorias = new ArrayList<>();


        public BuilderProduto(Builder builder) {
            this.builder = builder;
        }

        public BuilderProduto addCategoria(Categoria categoria) {
            this.categorias.add(categoria);
            return this;
        }

        public Produto builder() {
            return new Produto(builder.nome, builder.valorVenda, builder.valorCusto, this.categorias);
        }
    }

    public static class Builder {
        private String nome;
        private BigDecimal valorVenda;
        private BigDecimal valorCusto;

        public BuilderProdutoValorVenda nome(String nome) {
            this.nome = nome;
            return new BuilderProdutoValorVendaImp(this);
        }
    }
}

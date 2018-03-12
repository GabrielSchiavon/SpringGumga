package br.schiavon.dto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private String id;
    private String nome;
    private List<String> categoriasID;

    public ProdutoDTO() {}
    public ProdutoDTO(String id, String nome, List<String> categorias) {
        this.id = id;
        this.nome = nome;
        this.categoriasID = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getFkIdCategorias() {
        return categoriasID;
    }

    public void setFkIdCategorias(List<String> fkIdCategorias) {
        this.categoriasID = fkIdCategorias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Builder build() {
        return new Builder();
    }

    public static BuilderStep build2() {
        return new BuilderStep();
    }

    public static class BuilderStep {

        public NomeStep id(String id) {
            return new ProdutoDTOStepBuilder(id);
        }

        public class ProdutoDTOStepBuilder implements NomeStep, ProdutoDTOBuilder {
            private String id;
            private String nome;
            private List<String> categoriasID = new ArrayList<>();

            public ProdutoDTOStepBuilder(String id) {
                this.id = id;
            }

            @Override
            public ProdutoDTOBuilder nome(String nome) {
                this.nome = nome;
                return this;
            }

            @Override
            public ProdutoDTOBuilder idsCategorias(List<String> idsCategorias) {
                this.categoriasID.addAll(idsCategorias);
                return this;
            }

            @Override
            public ProdutoDTOBuilder idCategoria(String idCategoria) {
                this.categoriasID.add(idCategoria);
                return this;
            }

            @Override
            public ProdutoDTO builder() {
                return new ProdutoDTO(id, nome, categoriasID);
            }
        }

        public interface NomeStep {
            ProdutoDTOBuilder nome(String nome);
        }

        public interface ProdutoDTOBuilder {
            ProdutoDTOBuilder idsCategorias(List<String> idsCategorias);
            ProdutoDTOBuilder idCategoria(String idCategoria);
            ProdutoDTO builder();

        }
    }



    public static class Builder {
        private String id;
        private String nome;
        private List<String> categoriasID = new ArrayList<>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder addIDCategoria(String idCategoria) {
            this.categoriasID.add(idCategoria);
            return this;
        }

        public Builder addCategorias(List<String> categoriasID) {
            this.categoriasID = categoriasID;
            return this;
        }

        public ProdutoDTO builder() {
            return new ProdutoDTO(id, nome, categoriasID);
        }

    }
}

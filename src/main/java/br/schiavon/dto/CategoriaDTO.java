package br.schiavon.dto;

public class CategoriaDTO {

    private String id;
    private String nome;
    private Boolean perecivel;

    public CategoriaDTO() {}
    public CategoriaDTO(String id, String nome, Boolean perecivel) {
        this.id = id;
        this.nome = nome;
        this.perecivel = perecivel;
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

    public Boolean getPerecivel() {
        return perecivel;
    }

    public void setPerecivel(Boolean perecivel) {
        this.perecivel = perecivel;
    }

    public static BuilderStep build() {
        return new BuilderStep();
    }

    public static class BuilderStep {

        public NomeStep id(String id) {
            return new CategoriaDTOStepBuilder(id);
        }

        public class CategoriaDTOStepBuilder implements NomeStep, CategoriaDTOBuilder{

            private String id;
            private String nome;
            private Boolean perecivel;

            public CategoriaDTOStepBuilder(String id) {
                this.id = id;
            }

            @Override
            public CategoriaDTOBuilder nome(String nome) {
                this.nome = nome;
                return this;
            }

            @Override
            public CategoriaDTOBuilder perecivel(Boolean perecivel) {
                this.perecivel = perecivel;
                return this;
            }

            @Override
            public CategoriaDTO builder() {
                return new CategoriaDTO(id, nome, perecivel);
            }
        }

        public interface NomeStep {
            CategoriaDTOBuilder nome(String nome);
        }

        public interface CategoriaDTOBuilder {
            CategoriaDTOBuilder perecivel(Boolean perecivel);
            CategoriaDTO builder();
        }
    }
}
package br.schiavon.exception;

import java.util.Date;

public class DetalheErro {
    private Date data;
    private String mensagem;
    private String detalhes;

    public DetalheErro(String mensagem, String detalhes) {
        this.data = new Date();
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public Date getData() {
        return data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }
}

package br.com.itsolution.fintech.pix_api.dto;

public class AutenticacaoResponseDTO {
    private String banco;
    private String token;

    public AutenticacaoResponseDTO(String banco, String token) {
        this.banco = banco;
        this.token = token;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

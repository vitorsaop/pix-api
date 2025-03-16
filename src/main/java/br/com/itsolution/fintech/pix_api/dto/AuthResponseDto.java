package br.com.itsolution.fintech.pix_api.dto;

public class AuthResponseDto {

    private String banco;
    private String access_token;
    private String token_type;
    private String certificado;
    private long expires_in;

    public AuthResponseDto() {

    }

    public AuthResponseDto(String banco, String access_token, String token_type, String certificado, long expires_in) {
        this.banco = banco;
        this.access_token = access_token;
        this.token_type = token_type;
        this.certificado = certificado;
        this.expires_in = expires_in;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}

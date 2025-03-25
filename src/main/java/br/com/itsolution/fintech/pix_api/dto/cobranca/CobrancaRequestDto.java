package br.com.itsolution.fintech.pix_api.dto.cobranca;

import br.com.itsolution.fintech.pix_api.dto.bilhetagem.Bilhetavel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


public class CobrancaRequestDto implements Bilhetavel {

    @NotBlank(message = "O banco é obrigatório")
    private String banco;

    @NotBlank(message = "A chave PIX é obrigatória")
    private String chavePix;

    @Positive(message = "O valor deve ser maior que zero")
    private double valor;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "O tipo de cobrança é obrigatório")
    @Pattern(regexp = "INSTANTANEA|VENCIMENTO", message = "O tipo de cobrança deve ser INSTANTANEA ou VENCIMENTO")
    private String tipoCobranca;

    private String certificado;

    @NotBlank(message = "O token de autenticação é obrigatório")
    private String token;

    @NotBlank(message = "Necessário informar o cnpj da empresa emissora da cobrança")
    private String cnpj;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCnpj(String cnpj) {this.cnpj = cnpj; }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getAppId() {
        return "pix-api";
    }

    @Override
    public String getEndpoint() {
        return "/cobranca";
    }
}

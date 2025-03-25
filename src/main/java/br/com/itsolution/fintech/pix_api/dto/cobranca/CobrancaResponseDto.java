package br.com.itsolution.fintech.pix_api.dto.cobranca;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CobrancaResponseDto {

    private boolean criado;
    private String mensagem;
    private Object dados;

    public CobrancaResponseDto(boolean criado, String mensagem, Object dados) {
        this.criado = criado;
        this.mensagem = mensagem;
        this.dados = (dados != null) ? dados : Collections.emptyMap();
    }

    public boolean isCriado() {
        return criado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Object getDados() {
        return dados;
    }

    public static CobrancaResponseDto criado(String mensagem, Object dados) {
        return new CobrancaResponseDto(true, mensagem, dados);
    }

    public static CobrancaResponseDto erro(String mensagem) {
        return new CobrancaResponseDto(false, mensagem, null);
    }

}

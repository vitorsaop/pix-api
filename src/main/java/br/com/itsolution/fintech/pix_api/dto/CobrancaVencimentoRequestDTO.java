package br.com.itsolution.fintech.pix_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CobrancaVencimentoRequestDTO extends CobrancaRequestDTO {

    @NotBlank(message = "A data de vencimento é obrigatória para cobranças com vencimento")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data de vencimento deve estar no formato YYYY-MM-DD")
    private String dataVencimento;

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

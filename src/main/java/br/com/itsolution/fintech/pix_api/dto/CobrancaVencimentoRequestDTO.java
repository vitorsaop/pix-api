package br.com.itsolution.fintech.pix_api.dto;

public class CobrancaVencimentoRequestDTO extends CobrancaRequestDTO {

    private String dataVencimento;

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

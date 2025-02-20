package br.com.itsolution.fintech.pix_api.dto;

public class CobrancaResponseDTO {

    private String banco;
    private String qrCode;
    private String status;

    public CobrancaResponseDTO(String banco, String qrCode, String status) {
        this.banco = banco;
        this.qrCode = qrCode;
        this.status = status;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

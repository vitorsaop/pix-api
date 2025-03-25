package br.com.itsolution.fintech.pix_api.dto.bilhetagem;

public class BillingMessageDto {

    private String cnpj;
    private String appId;
    private String endpoint;


    public BillingMessageDto() {}

    public BillingMessageDto(String cnpj, String appId, String endpoint) {
        this.cnpj = cnpj;
        this.appId = appId;
        this.endpoint = endpoint;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}

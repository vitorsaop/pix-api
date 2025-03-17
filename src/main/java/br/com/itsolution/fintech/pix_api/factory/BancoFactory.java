package br.com.itsolution.fintech.pix_api.factory;

import br.com.itsolution.fintech.pix_api.service.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BancoFactory {

    private final Map<String, AuthProvider> authProviders;
    private final Map<String, CobrancaProvider> cobrancaProviders;

    public BancoFactory(BancoBrasilService bancoBrasilService,
                        BancoItauService bancoItauService,
                        BancoSantanderService bancoSantanderService) {

        this.authProviders = Map.of(
                "BANCO_DO_BRASIL", bancoBrasilService,
                "BANCO_ITAU", bancoItauService,
                "BANCO_SANTANDER", bancoSantanderService
        );

        this.cobrancaProviders = Map.of(
                "BANCO_DO_BRASIL", bancoBrasilService,
                "BANCO_ITAU", bancoItauService,
                "BANCO_SANTANDER", bancoSantanderService
        );
    }

    public AuthProvider getAuthProvider(String banco) {
        return authProviders.getOrDefault(banco, null);
    }

    public CobrancaProvider getCobrancaProvider(String banco) {
        return cobrancaProviders.getOrDefault(banco, null);
    }

}

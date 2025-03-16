package br.com.itsolution.fintech.pix_api.factory;

import br.com.itsolution.fintech.pix_api.service.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthFactory {

    private final Map<String, AuthProvider> providers;

    public AuthFactory(BancoBrasilService bancoBrasilService, BancoItauService bancoItauService, BancoSantanderService bancoSantanderService) {
        this.providers = Map.of(
                "BANCO_DO_BRASIL", bancoBrasilService,
                "BANCO_ITAU", bancoItauService,
                "BANCO_SANTANDER", bancoSantanderService
        );
    }


    public AuthProvider getAuthProvider(String banco) {
        return providers.getOrDefault(banco, null);
    }

}

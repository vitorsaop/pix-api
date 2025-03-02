package br.com.itsolution.fintech.pix_api.factory;

import br.com.itsolution.fintech.pix_api.service.AuthProvider;
import br.com.itsolution.fintech.pix_api.service.BancoBrasilService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthFactory {

    private final Map<String, AuthProvider> providers;

    public AuthFactory(BancoBrasilService bancoBrasilService) {
        this.providers = Map.of(
                "BANCO_DO_BRASIL", bancoBrasilService
        );
    }

    public AuthProvider getAuthProvider(String banco) {
        return providers.getOrDefault(banco, null);
    }

}

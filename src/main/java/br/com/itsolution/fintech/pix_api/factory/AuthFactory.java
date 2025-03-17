package br.com.itsolution.fintech.pix_api.factory;

import br.com.itsolution.fintech.pix_api.service.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthFactory {

    private final BancoFactory bancoFactory;

    public AuthFactory(BancoFactory bancoFactory) {
        this.bancoFactory = bancoFactory;
    }

    public AuthProvider getAuthProvider(String banco) {
        return bancoFactory.getAuthProvider(banco);
    }

}

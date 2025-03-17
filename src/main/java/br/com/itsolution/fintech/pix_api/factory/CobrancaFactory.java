package br.com.itsolution.fintech.pix_api.factory;

import br.com.itsolution.fintech.pix_api.service.CobrancaProvider;
import org.springframework.stereotype.Component;

@Component
public class CobrancaFactory {

    private final BancoFactory bancoFactory;

    public CobrancaFactory(BancoFactory bancoFactory) {
        this.bancoFactory = bancoFactory;
    }

    public CobrancaProvider getCobrancaProvider(String banco) {
        return bancoFactory.getCobrancaProvider(banco);
    }

}

package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.client.AutenticacaoFeignClient;
import br.com.itsolution.fintech.pix_api.dto.AutenticacaoRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.AutenticacaoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AutenticacaoService {

    @Autowired
    private AutenticacaoFeignClient autenticacaoFeignClient;

    private static final Map<String, String> URLS_BANCOS = new HashMap<>() {{
        put("ITAU", "https://api.itau.com/auth");
        put("BANCO_DO_BRASIL", "https://api.bb.com/auth");
        put("SANTANDER", "https://api.santander.com/auth");
        put("BRADESCO", "https://api.bradesco.com/auth");
        put("SICOOB", "https://api.sicoob.com/auth");
        put("NUBANK", "https://api.nubank.com/auth");
    }};

    public AutenticacaoResponseDTO autenticar(AutenticacaoRequestDTO request) {
        String url = URLS_BANCOS.getOrDefault(request.getBanco().toUpperCase(), null);

        if (url == null) {
            throw new IllegalArgumentException("Banco não suportado: " + request.getBanco());
        }

        if (autenticacaoFeignClient == null) {
            throw new IllegalStateException("Feign Client não foi injetado corretamente.");
        }

        return autenticacaoFeignClient.autenticar(request);
    }
}

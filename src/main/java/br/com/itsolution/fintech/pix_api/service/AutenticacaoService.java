package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.dto.AutenticacaoRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.AutenticacaoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class AutenticacaoService {

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

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> body = new HashMap<>();
        body.put("client_id", request.getClientId());
        body.put("client_secret", request.getClientSecret());
        body.put("grant_type", request.getGrantType());
        body.put("scope", request.getEscopo());

        String token = "token_fake_" + request.getBanco().toLowerCase(); // Simulação de resposta

        return new AutenticacaoResponseDTO(request.getBanco(), token);
    }
}

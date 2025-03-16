package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.dto.AuthRequestDto;
import br.com.itsolution.fintech.pix_api.dto.CobrancaRequestDto;
import br.com.itsolution.fintech.pix_api.dto.CobrancaVencimentoRequestDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class BancoSantanderService implements AuthProvider, CobrancaProvider {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public BancoSantanderService(@Value("${santander.auth-url}") String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public String obterToken(AuthRequestDto request) {
        try {
            // 🔹 URL final com `grant_type` como parâmetro na URL
            String authUrl = baseUrl + "/sandbox/oauth/token?grant_type=client_credentials";

            System.out.println("Banco Santander - Iniciando autenticação...");
            System.out.println("URL de autenticação: " + authUrl);

            // 🔹 Criar Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // 🔹 Criar Corpo da Requisição (`x-www-form-urlencoded`)
            String body = "client_id=" + URLEncoder.encode(request.getClientId(), StandardCharsets.UTF_8) +
                    "&client_secret=" + URLEncoder.encode(request.getClientSecret(), StandardCharsets.UTF_8);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            // 🔹 Fazer Requisição para o Santander
            ResponseEntity<Map> response = restTemplate.exchange(
                    authUrl,  // ✅ `grant_type=client_credentials` já na URL
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Autenticação bem-sucedida no Banco Santander.");
                return (String) response.getBody().get("access_token");
            } else {
                System.out.println("Erro ao obter token: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar no Santander: " + e.getMessage());
            return "Erro ao autenticar no Santander: " + e.getMessage();
        }
    }

    @Override
    public String cobranca(CobrancaRequestDto request) {
        return "";
    }

    @Override
    public String cobrancaVencimento(CobrancaVencimentoRequestDto request) {
        return "";
    }
}

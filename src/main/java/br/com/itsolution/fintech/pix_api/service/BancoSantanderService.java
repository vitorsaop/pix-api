package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.annotation.Bilhetar;
import br.com.itsolution.fintech.pix_api.dto.autenticacao.AuthRequestDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaRequestDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaResponseDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaVencimentoRequestDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
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
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

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

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        return headers;
    }

    private Map<String, Object> createCobrancaRequestBody(CobrancaRequestDto request) {
        Map<String, Object> body = new HashMap<>();
        body.put("calendario", Map.of("expiracao", 3600));
        body.put("devedor", Map.of("cnpj", "12345678000195", "nome", "Empresa de Serviços SA"));
        body.put("valor", Map.of("original", String.format("%.2f", request.getValor())));
        body.put("chave", request.getChavePix());
        body.put("solicitacaoPagador", request.getDescricao());

        return body;
    }

    @Bilhetar
    @Override
    public CobrancaResponseDto cobranca(CobrancaRequestDto request) {
        try {
            String cobrancaUrl = baseUrl + "/api/v1/sandbox/cob/TESTE-123b-12343-b13123-213231";

            System.out.println("Banco Santander - Criando cobrança PIX...");
            System.out.println("URL de cobrança: " + cobrancaUrl);

            HttpHeaders headers = createHeaders(request.getToken());

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(createCobrancaRequestBody(request), headers);

            //Fazendo a request p/ criação da cobrança
            ResponseEntity<Map> response = restTemplate.exchange(
                    cobrancaUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("Cobrança PIX criada com sucesso.");
                return CobrancaResponseDto.criado("Cobrança PIX criada com sucesso", response.getBody());

            } else {
                System.out.println("Erro ao criar cobrança: " + response.getStatusCode());
                return CobrancaResponseDto.erro("Cobrança não foi criada " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar cobrança no Santander: " + e.getMessage());
            return CobrancaResponseDto.erro("Erro ao criar cobrança no Santander " + e.getMessage());
            //return "Erro ao criar cobrança no Santander: " + e.getMessage();
        }
    }

    @Override
    public String cobrancaVencimento(CobrancaVencimentoRequestDto request) {
        return "";
    }
}

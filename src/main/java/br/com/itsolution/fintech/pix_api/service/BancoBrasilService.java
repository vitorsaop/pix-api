package br.com.itsolution.fintech.pix_api.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import br.com.itsolution.fintech.pix_api.dto.AuthRequestDto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class BancoBrasilService implements AuthProvider {

    private final RestTemplate restTemplate;
    private final String authUrl;

    public BancoBrasilService(@Value("${bancobrasil.auth-url}") String authUrl, RestTemplate restTemplate) {
        this.authUrl = authUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public String obterToken(AuthRequestDto request) {
        try {
            String auth = request.getClientId() + ":" + request.getClientSecret();
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            System.out.println("Banco do Brasil - Iniciando autenticação...");
            System.out.println("URL de autenticação: "+ authUrl + "/oauth/token");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Basic " + encodedAuth);

            String body = "grant_type=" + request.getGrantType() + "&scope=" + request.getEscopo();

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    authUrl + "/oauth/token",  // 🔹 Certifique-se de que a URL é absoluta!
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Autenticação bem-sucedida no Banco do Brasil.");
                return (String) response.getBody().get("access_token");
            } else {
                System.out.println("Erro ao obter token: "+ response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Falha na autenticação do Banco do Brasil: "+ e.getMessage());
            return null;
        }
    }

}

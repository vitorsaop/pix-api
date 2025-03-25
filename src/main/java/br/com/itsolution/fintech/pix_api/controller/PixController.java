package br.com.itsolution.fintech.pix_api.controller;

import br.com.itsolution.fintech.pix_api.dto.autenticacao.AuthRequestDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaRequestDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaResponseDto;
import br.com.itsolution.fintech.pix_api.factory.AuthFactory;
import br.com.itsolution.fintech.pix_api.factory.CobrancaFactory;
import br.com.itsolution.fintech.pix_api.service.AuthProvider;
import br.com.itsolution.fintech.pix_api.service.CobrancaProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pix")
@Validated
public class PixController {

    private final AuthFactory authFactory;
    private final CobrancaFactory cobrancaFactory;

    public PixController(AuthFactory authFactory, CobrancaFactory cobrancaFactory) {
        this.authFactory     = authFactory;
        this.cobrancaFactory = cobrancaFactory;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> obterToken(@RequestBody AuthRequestDto request) {
        AuthProvider authProvider = authFactory.getAuthProvider(request.getBanco());

        if (authProvider == null) {
            return ResponseEntity.badRequest().body("Banco não suportado!");
        }

        String token = authProvider.obterToken(request);
        if (token == null) {
            return ResponseEntity.status(500).body("Erro ao obter token!");
        }

        return ResponseEntity.ok(token);
    }

    /**
     * 🔹 Cria uma cobrança PIX para um banco específico.
     */
    @PostMapping("/cobranca")
    public ResponseEntity<CobrancaResponseDto> criarCobranca(@RequestBody @Valid CobrancaRequestDto request) {
        CobrancaProvider cobrancaProvider = cobrancaFactory.getCobrancaProvider(request.getBanco());

        if (cobrancaProvider == null) {
            return ResponseEntity.badRequest().body(CobrancaResponseDto.erro("Banco não suportado para cobrança!"));
        }

        CobrancaResponseDto cobrancaResponse = cobrancaProvider.cobranca(request);

        return ResponseEntity.ok(cobrancaResponse);
    }

}

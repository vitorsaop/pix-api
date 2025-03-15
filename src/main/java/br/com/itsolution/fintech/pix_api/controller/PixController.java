package br.com.itsolution.fintech.pix_api.controller;

import br.com.itsolution.fintech.pix_api.dto.AuthRequestDto;
import br.com.itsolution.fintech.pix_api.factory.AuthFactory;
import br.com.itsolution.fintech.pix_api.service.AuthProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pix/auth")
@Validated
public class PixController {

    private final AuthFactory authFactory;

    public PixController(AuthFactory authFactory) {
        this.authFactory = authFactory;
    }

    @PostMapping("/{banco}")
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

}

package br.com.itsolution.fintech.pix_api.controller;

import br.com.itsolution.fintech.pix_api.dto.AutenticacaoRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.AutenticacaoResponseDTO;
import br.com.itsolution.fintech.pix_api.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @Autowired
    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    public ResponseEntity<AutenticacaoResponseDTO> autenticar(@RequestBody AutenticacaoRequestDTO request) {
        AutenticacaoResponseDTO response = autenticacaoService.autenticar(request);
        return ResponseEntity.ok(response);
    }

}

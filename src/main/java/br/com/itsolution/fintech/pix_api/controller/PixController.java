package br.com.itsolution.fintech.pix_api.controller;

import br.com.itsolution.fintech.pix_api.dto.CobrancaRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.CobrancaResponseDTO;
import br.com.itsolution.fintech.pix_api.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pix")
@Validated
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping("/cobranca")
    public ResponseEntity<CobrancaResponseDTO> gerarCobranca(@RequestBody CobrancaRequestDTO request) {
        CobrancaResponseDTO response = pixService.gerarCobranca(request);
        return ResponseEntity.ok(response);
    }
}

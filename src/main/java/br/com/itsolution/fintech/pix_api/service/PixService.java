package br.com.itsolution.fintech.pix_api.service;


import br.com.itsolution.fintech.pix_api.dto.CobrancaRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.CobrancaResponseDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PixService {

    private static final Map<String, String> URLS_BANCOS = new HashMap<>() {{
        put("ITAU", "https://api.itau.com/pix");
        put("BANCO_DO_BRASIL", "https://api.bb.com.br/pix");
        put("SANTANDER", "https://api.santander.com.br/pix");
        put("BRADESCO", "https://api.bradesco.com.br/pix");
        put("SICOOB", "https://api.sicoob.com.br/pix");
        put("NUBANK", "https://api.nubank.com.br/pix");
    }};

    public CobrancaResponseDTO gerarCobranca(CobrancaRequestDTO request) {
        String url = URLS_BANCOS.getOrDefault(request.getBanco().toUpperCase(), null);

        if (url == null) {
            throw new IllegalArgumentException("Banco não suportado: " + request.getBanco());
        }

        // Simulando geração de QR Code PIX
        String qrCode = "qrcode_simulado_" + UUID.randomUUID();
        String status = "CRIADO";

        return new CobrancaResponseDTO(request.getBanco(), qrCode, status);
    }

}

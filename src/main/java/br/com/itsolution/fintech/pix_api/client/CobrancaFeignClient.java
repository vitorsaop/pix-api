package br.com.itsolution.fintech.pix_api.client;

import br.com.itsolution.fintech.pix_api.dto.CobrancaRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.CobrancaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banco-cobranca-client", url = "https://api.banco.com/pix/cobranca")
public interface CobrancaFeignClient {

    @PostMapping
    CobrancaResponseDTO gerarCobranca(@RequestBody CobrancaRequestDTO request);

}

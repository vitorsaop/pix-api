package br.com.itsolution.fintech.pix_api.client;

import br.com.itsolution.fintech.pix_api.dto.AutenticacaoRequestDTO;
import br.com.itsolution.fintech.pix_api.dto.AutenticacaoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "banco-auth-client", url = "https://api.banco.com/auth")
public interface AutenticacaoFeignClient {

    @PostMapping
    AutenticacaoResponseDTO autenticar(@RequestBody AutenticacaoRequestDTO request);

}

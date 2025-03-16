package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.dto.CobrancaRequestDto;
import br.com.itsolution.fintech.pix_api.dto.CobrancaVencimentoRequestDto;

public interface CobrancaProvider {
    String cobranca(CobrancaRequestDto request);
    String cobrancaVencimento(CobrancaVencimentoRequestDto request);
}

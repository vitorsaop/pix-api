package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaRequestDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaResponseDto;
import br.com.itsolution.fintech.pix_api.dto.cobranca.CobrancaVencimentoRequestDto;

public interface CobrancaProvider {
    CobrancaResponseDto cobranca(CobrancaRequestDto request);
    String cobrancaVencimento(CobrancaVencimentoRequestDto request);
}

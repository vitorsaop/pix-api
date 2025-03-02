package br.com.itsolution.fintech.pix_api.service;

import br.com.itsolution.fintech.pix_api.dto.AuthRequestDto;

public interface AuthProvider {
    String obterToken(AuthRequestDto request);
}

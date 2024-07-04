package br.com.ntconsult.desafio_fairfax.service;

import br.com.ntconsult.desafio_fairfax.dtos.AuthDto;
import br.com.ntconsult.desafio_fairfax.requests.LoginRequest;

public interface LoginService {
    AuthDto doLogin(LoginRequest request);
}

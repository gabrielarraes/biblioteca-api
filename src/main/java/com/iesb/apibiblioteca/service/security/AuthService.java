package com.iesb.apibiblioteca.service.security;

import com.iesb.apibiblioteca.payload.request.auth.SignInRequest;
import com.iesb.apibiblioteca.payload.response.auth.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signIn(SignInRequest signInRequest);
}

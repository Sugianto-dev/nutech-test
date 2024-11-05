package com.sugianto.nutech.service;

import com.sugianto.nutech.exception.UnauthorizedLoginException;
import com.sugianto.nutech.request.auth.LoginRequest;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.response.auth.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtService jwtService;

    public WebResponse<LoginResponse> login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            throw new UnauthorizedLoginException("Username atau password salah");
        }

        String token = jwtService.generateToken(myUserDetailsService.loadUserByUsername(request.getEmail()));

        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return WebResponse.<LoginResponse>builder()
                .status(0)
                .message("Login Sukses")
                .data(response)
                .build();
    }

}

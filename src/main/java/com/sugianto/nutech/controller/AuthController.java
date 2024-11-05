package com.sugianto.nutech.controller;

import com.sugianto.nutech.request.auth.LoginRequest;
import com.sugianto.nutech.request.auth.RegistrationRequest;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.response.auth.LoginResponse;
import com.sugianto.nutech.service.LoginService;
import com.sugianto.nutech.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    LoginService loginService;

    @PostMapping("/registration")
    public WebResponse<String> registration(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.registration(request);
    }

    @PostMapping("/login")
    public WebResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return loginService.login(request);
    }

}

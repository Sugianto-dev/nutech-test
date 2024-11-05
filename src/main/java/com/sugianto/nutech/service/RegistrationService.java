package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.AppUser;
import com.sugianto.nutech.exception.UserAlreadyRegistredException;
import com.sugianto.nutech.repository.AppUserRepository;
import com.sugianto.nutech.request.auth.RegistrationRequest;
import com.sugianto.nutech.response.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    AppUserRepository appUserRepository;

    public WebResponse<String> registration(RegistrationRequest request) {
        AppUser appUser = appUserRepository.findByEmail(request.getEmail());

        if (appUser == null) {
            AppUser appUser2 = new AppUser();
            appUser2.setEmail(request.getEmail());
            appUser2.setFirstName(request.getFirstName());
            appUser2.setLastName(request.getLastName());
            appUser2.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

            appUserRepository.save(appUser2);
        } else {
            throw new UserAlreadyRegistredException("User sudah diregistrasi");
        }

        return WebResponse.<String>builder()
                .status(0)
                .message("Registrasi berhasil silahkan login")
                .data(null)
                .build();
    }

}

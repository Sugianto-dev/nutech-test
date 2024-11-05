package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.AppUser;
import com.sugianto.nutech.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);

        if (email == null || appUser == null) {
            throw new UsernameNotFoundException("not found");
        }

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .build();
    }

}

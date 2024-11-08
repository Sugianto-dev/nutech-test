package com.sugianto.nutech.repository;

import com.sugianto.nutech.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByEmail(String email);

}

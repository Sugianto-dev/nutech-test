package com.sugianto.nutech.repository;

import com.sugianto.nutech.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, String> {
}

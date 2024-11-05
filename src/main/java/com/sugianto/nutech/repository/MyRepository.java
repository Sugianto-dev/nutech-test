package com.sugianto.nutech.repository;

import com.sugianto.nutech.exception.LessBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class MyRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void topupQuery(String email, BigDecimal topupAmount) {
        String selectSql = "SELECT balance FROM balance WHERE email = ? FOR UPDATE";
        BigDecimal balance = jdbcTemplate.queryForObject(selectSql, BigDecimal.class, email);

        String updateSql = "UPDATE balance SET balance = balance + ? WHERE email = ?";
        jdbcTemplate.update(updateSql, topupAmount, email);
    }

    public void transactions(String email, BigDecimal serviceTarif) {
        String selectSql = "SELECT balance FROM balance WHERE email = ? FOR UPDATE";
        BigDecimal balance = jdbcTemplate.queryForObject(selectSql, BigDecimal.class, email);

        if (balance.compareTo(serviceTarif) < 0) {
            throw new LessBalanceException("Saldo anda tidak mencukupi");
        }

        String updateSql = "UPDATE balance SET balance = balance - ? WHERE email = ?";
        jdbcTemplate.update(updateSql, serviceTarif, email);
    }

}

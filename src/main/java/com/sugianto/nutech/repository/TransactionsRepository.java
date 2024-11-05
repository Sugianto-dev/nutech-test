package com.sugianto.nutech.repository;

import com.sugianto.nutech.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, String> {

    @Query(value = "SELECT count(invoice_number) FROM transactions WHERE date(created_on) = :date", nativeQuery = true)
    long countByCreatedOnToday(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM transactions ORDER BY created_on DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Transactions> findTransactionsWithLimitAndOffset(@Param("limit") Integer limit, @Param("offset") Integer offset);

}

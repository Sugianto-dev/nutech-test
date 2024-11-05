package com.sugianto.nutech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "transaction_type")
    private String transactionType;

    private String description;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "created_on")
    private OffsetDateTime createdOn;

}

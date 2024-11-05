package com.sugianto.nutech.response.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionsResponse {

    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("created_on")
    private OffsetDateTime createdOn;

}

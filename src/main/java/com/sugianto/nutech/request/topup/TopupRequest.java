package com.sugianto.nutech.request.topup;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopupRequest {

    @Min(value = 0, message = "Parameter amount hanya boleh angka dan tidak boleh lebih kecil dari 0")
    @Digits(integer = 10, fraction = 0, message = "Parameter amount hanya boleh angka dan tidak boleh lebih kecil dari 0")
    @JsonProperty("top_up_amount")
    private BigDecimal topupAmount;

}

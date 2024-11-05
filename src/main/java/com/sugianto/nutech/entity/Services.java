package com.sugianto.nutech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service")
public class Services {

    @Id
    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_icon")
    private String serviceIcon;

    @Column(name = "service_tarif")
    private BigDecimal serviceTarif;

}

package com.sugianto.nutech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "banner")
public class Banner {

    @Id
    @Column(name = "banner_name")
    private String bannerName;

    @Column(name = "banner_image")
    private String bannerImage;

    private String description;

}

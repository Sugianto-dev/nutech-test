package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.Banner;
import com.sugianto.nutech.repository.BannerRepository;
import com.sugianto.nutech.response.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    @Autowired
    BannerRepository bannerRepository;

    public WebResponse<List<Banner>> getBanner() {
        List<Banner> bannerList = bannerRepository.findAll();

        return WebResponse.<List<Banner>>builder()
                .status(0)
                .message("Sukses")
                .data(bannerList)
                .build();
    }

}

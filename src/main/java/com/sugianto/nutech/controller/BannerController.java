package com.sugianto.nutech.controller;

import com.sugianto.nutech.entity.Banner;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping
    public WebResponse<List<Banner>> getBanner() {
        return bannerService.getBanner();
    }

}

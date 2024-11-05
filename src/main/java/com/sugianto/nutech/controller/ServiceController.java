package com.sugianto.nutech.controller;

import com.sugianto.nutech.entity.Services;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping
    public WebResponse<List<Services>> getServices() {
        return serviceService.getServices();
    }

}

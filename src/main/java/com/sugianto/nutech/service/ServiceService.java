package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.Services;
import com.sugianto.nutech.repository.ServiceRepository;
import com.sugianto.nutech.response.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public WebResponse<List<Services>> getServices() {
        List<Services> servicesList = serviceRepository.findAll();

        return WebResponse.<List<Services>>builder()
                .status(0)
                .message("Sukses")
                .data(servicesList)
                .build();
    }

}

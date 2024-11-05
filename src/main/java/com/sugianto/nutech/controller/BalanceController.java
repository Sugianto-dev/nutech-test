package com.sugianto.nutech.controller;

import com.sugianto.nutech.entity.Balance;
import com.sugianto.nutech.request.topup.TopupRequest;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.service.BalanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @GetMapping("/balance")
    public WebResponse<Optional<Balance>> getBalance() {
        return balanceService.getBalance();
    }

    @PostMapping("/topup")
    public WebResponse<Optional<Balance>> topup(@Valid @RequestBody TopupRequest request) {
        return balanceService.topup(request);
    }

}

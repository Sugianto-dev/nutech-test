package com.sugianto.nutech.controller;

import com.sugianto.nutech.entity.Transactions;
import com.sugianto.nutech.request.transaction.TransactionsRequest;
import com.sugianto.nutech.response.PagingResponse;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.response.transaction.TransactionsResponse;
import com.sugianto.nutech.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;

    @PostMapping
    public WebResponse<TransactionsResponse> transaction(@Valid @RequestBody TransactionsRequest request) {
        return transactionsService.transaction(request);
    }

    @GetMapping("/history")
    public WebResponse<PagingResponse<List<Transactions>>> transactionHistory(
            @RequestParam(value = "limit", defaultValue = "0") Integer limit,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset) {
        return transactionsService.transactionHistory(limit, offset);
    }

}

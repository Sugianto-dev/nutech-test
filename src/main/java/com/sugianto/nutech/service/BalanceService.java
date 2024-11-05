package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.Balance;
import com.sugianto.nutech.entity.Transactions;
import com.sugianto.nutech.repository.BalanceRepository;
import com.sugianto.nutech.repository.MyRepository;
import com.sugianto.nutech.repository.TransactionsRepository;
import com.sugianto.nutech.request.topup.TopupRequest;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.util.GenerateInvoiceNumberUtil;
import com.sugianto.nutech.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    MyRepository myRepository;

    public WebResponse<Optional<Balance>> getBalance() {
        Optional<Balance> balance = balanceRepository.findById(SecurityUtil.getPayloadToken());

        return WebResponse.<Optional<Balance>>builder()
                .status(0)
                .message("Get Balance Berhasil")
                .data(balance)
                .build();
    }

    @Transactional
    public WebResponse<Optional<Balance>> topup(TopupRequest request) {
        myRepository.topupQuery(SecurityUtil.getPayloadToken(), request.getTopupAmount());

        long count = transactionsRepository.countByCreatedOnToday(LocalDate.now());
        String invoiceNumber = GenerateInvoiceNumberUtil.generateInvoiceNumber(count);

        Transactions transactions = new Transactions();
        transactions.setInvoiceNumber(invoiceNumber);
        transactions.setTransactionType("TOPUP");
        transactions.setDescription("Top Up balance");
        transactions.setTotalAmount(request.getTopupAmount());
        transactions.setCreatedOn(OffsetDateTime.now());

        transactionsRepository.save(transactions);

        Optional<Balance> balance = balanceRepository.findById(SecurityUtil.getPayloadToken());

        return WebResponse.<Optional<Balance>>builder()
                .status(0)
                .message("Top Up Balance berhasil")
                .data(balance)
                .build();
    }

}

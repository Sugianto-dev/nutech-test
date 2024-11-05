package com.sugianto.nutech.service;

import com.sugianto.nutech.entity.Services;
import com.sugianto.nutech.entity.Transactions;
import com.sugianto.nutech.exception.ServiceNotFoundException;
import com.sugianto.nutech.repository.MyRepository;
import com.sugianto.nutech.repository.ServiceRepository;
import com.sugianto.nutech.repository.TransactionsRepository;
import com.sugianto.nutech.request.transaction.TransactionsRequest;
import com.sugianto.nutech.response.PagingResponse;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.response.transaction.TransactionsResponse;
import com.sugianto.nutech.util.GenerateInvoiceNumberUtil;
import com.sugianto.nutech.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    MyRepository myRepository;

    @Transactional
    public WebResponse<TransactionsResponse> transaction(TransactionsRequest request) {
        Optional<Services> services = serviceRepository.findById(request.getServiceCode());

        if (services.isEmpty()) {
            throw new ServiceNotFoundException("Service ataus Layanan tidak ditemukan");
        }

        myRepository.transactions(SecurityUtil.getPayloadToken(), services.get().getServiceTarif());

        long count = transactionsRepository.countByCreatedOnToday(LocalDate.now());
        String invoiceNumber = GenerateInvoiceNumberUtil.generateInvoiceNumber(count);

        Transactions transactions = new Transactions();
        transactions.setInvoiceNumber(invoiceNumber);
        transactions.setTransactionType("PAYMENT");
        transactions.setDescription(services.get().getServiceName());
        transactions.setTotalAmount(services.get().getServiceTarif());
        transactions.setCreatedOn(OffsetDateTime.now());

        transactionsRepository.save(transactions);

        TransactionsResponse response = new TransactionsResponse();
        response.setInvoiceNumber(transactions.getInvoiceNumber());
        response.setServiceCode(services.get().getServiceCode());
        response.setServiceName(services.get().getServiceName());
        response.setTransactionType(transactions.getTransactionType());
        response.setTotalAmount(transactions.getTotalAmount());
        response.setCreatedOn(transactions.getCreatedOn());

        return WebResponse.<TransactionsResponse>builder()
                .status(0)
                .message("Transaksi berhasil")
                .data(response)
                .build();
    }

    public WebResponse<PagingResponse<List<Transactions>>> transactionHistory(Integer limit, Integer offset) {
        if (limit.equals(0)) {
            limit = Integer.parseInt(String.valueOf(transactionsRepository.count()));
        }

        List<Transactions> transactionsList = transactionsRepository.findTransactionsWithLimitAndOffset(limit, offset);

        PagingResponse<List<Transactions>> pagingResponse = PagingResponse.<List<Transactions>>builder()
                .offset(offset)
                .limit(limit)
                .records(transactionsList)
                .build();

        return WebResponse.<PagingResponse<List<Transactions>>>builder()
                .status(0)
                .message("Get History Berhasil")
                .data(pagingResponse)
                .build();
    }

}

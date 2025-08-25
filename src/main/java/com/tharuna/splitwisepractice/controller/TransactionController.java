package com.tharuna.splitwisepractice.controller;

import com.tharuna.splitwisepractice.dto.response.TransactionResponseDto;
import com.tharuna.splitwisepractice.model.Transaction;
import com.tharuna.splitwisepractice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction/{groupId}")
    public ResponseEntity<List<TransactionResponseDto>> requestTransaction(@PathVariable Long groupId) {
        return ResponseEntity.ok(transactionService.getTransactoinForGroup(groupId));
    }
}

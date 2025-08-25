package com.tharuna.splitwisepractice.controller;

import com.tharuna.splitwisepractice.dto.request.ExpenseRequestDto;
import com.tharuna.splitwisepractice.dto.response.ExpenseResponseDto;
import com.tharuna.splitwisepractice.model.Expense;
import com.tharuna.splitwisepractice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<ExpenseResponseDto> addExpense(@RequestBody ExpenseRequestDto expenseRequestDto) {
        System.out.println("ExpenseRequestDto : "+expenseRequestDto);
        return ResponseEntity.ok(expenseService.addExpense(expenseRequestDto));
    }
}

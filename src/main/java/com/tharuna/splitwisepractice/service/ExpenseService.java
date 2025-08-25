package com.tharuna.splitwisepractice.service;

import com.tharuna.splitwisepractice.dto.request.ExpenseRequestDto;
import com.tharuna.splitwisepractice.dto.request.PaymentDto;
import com.tharuna.splitwisepractice.dto.response.ExpenseResponseDto;
import com.tharuna.splitwisepractice.dto.response.LedgerResponseDto;
import com.tharuna.splitwisepractice.model.Expense;
import com.tharuna.splitwisepractice.model.PaymentLedger;
import com.tharuna.splitwisepractice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    public ExpenseResponseDto addExpense(ExpenseRequestDto expenseRequestDto) {

    Expense expense = new Expense();
    expense.setAmount(expenseRequestDto.getAmount());
    expense.setDescription(expenseRequestDto.getDescription());
    expense.setGroup(groupService.getGroupById(expenseRequestDto.getGroupId()));

    double amountOwed = expenseRequestDto.getAmount() / expenseRequestDto.getContributors().size();

    List<PaymentLedger> paymentLedgers = new ArrayList<>();

    for (Long userId : expenseRequestDto.getContributors()) {
        double amountPaid = expenseRequestDto.getPayments().stream()
                .filter(payment -> payment.getUserId().equals(userId))
                .mapToDouble(PaymentDto::getAmount)
                .sum();

        PaymentLedger ledger = new PaymentLedger();
        ledger.setId(null); // ensures new entity
        ledger.setUser(userService.getUserById(userId));
        ledger.setExpense(expense);
        ledger.setAmountPaid(amountPaid);
        ledger.setAmountOwed(amountOwed);

        paymentLedgers.add(ledger);
    }

    expense.setPaymentLedgers(paymentLedgers);

    // Save once; cascade will save the ledgers
    expense = expenseRepository.save(expense);

    return mapToExpenseResponseDto(expense);
}

    public Expense getExpenseById(long id) {
        return expenseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Expense not found with id: " + id));
    }

    public ExpenseResponseDto mapToExpenseResponseDto(Expense expense) {
        ExpenseResponseDto responseDto = new ExpenseResponseDto();
        responseDto.setId(expense.getId());
        responseDto.setDescription(expense.getDescription());
        responseDto.setAmount(expense.getAmount());

        List<LedgerResponseDto> ledgers = new ArrayList<>();
        for (PaymentLedger ledger : expense.getPaymentLedgers()) {
            LedgerResponseDto ledgerResponse = new LedgerResponseDto();
            ledgerResponse.setUserId(ledger.getUser().getId());
            ledgerResponse.setAmountPaid(ledger.getAmountPaid());
            ledgerResponse.setAmountOwed(ledger.getAmountOwed());
            ledgers.add(ledgerResponse);
        }

        responseDto.setLedgers(ledgers);
        return responseDto;
    }
}

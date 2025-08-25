package com.tharuna.splitwisepractice.service;

import com.tharuna.splitwisepractice.model.Expense;
import com.tharuna.splitwisepractice.model.PaymentLedger;
import com.tharuna.splitwisepractice.model.User;
import com.tharuna.splitwisepractice.repository.PaymentLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.PrimitiveIterator;

@Service
public class PaymentLedgerService {

    @Autowired
    private PaymentLedgerRepository paymentLedgerRepository;


    public PaymentLedger createPayment(
            Expense expense,
            User user,
            Double amountPaid,
            Double amountOwed) {
        PaymentLedger paymentLedger = new PaymentLedger();
        paymentLedger.setUser(user);
        paymentLedger.setExpense(expense);
        paymentLedger.setAmountPaid(amountPaid);
        paymentLedger.setAmountOwed(amountOwed);

        return paymentLedgerRepository.save(paymentLedger);
    }
}

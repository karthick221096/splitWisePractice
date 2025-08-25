package com.tharuna.splitwisepractice.service;

import com.tharuna.splitwisepractice.dto.response.TransactionResponseDto;
import com.tharuna.splitwisepractice.model.Expense;
import com.tharuna.splitwisepractice.model.PaymentLedger;
import com.tharuna.splitwisepractice.model.Transaction;
import com.tharuna.splitwisepractice.model.UserBalance;
import com.tharuna.splitwisepractice.repository.TransactionRepository;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public GroupService groupService;

    public List<TransactionResponseDto> getTransactoinForGroup(Long groupId) {
        List<Expense> expenses = groupService.findExpenseList(groupId);
        Map<Long,Double> balanceMap = new HashMap<>();

        for (Expense expense : expenses) {
            for (PaymentLedger ledger : expense.getPaymentLedgers()) {
                Long userId = ledger.getUser().getId();
                double netAmount = ledger.getAmountPaid() - ledger.getAmountOwed();
                balanceMap.put(userId, balanceMap.getOrDefault(userId, 0.0) + netAmount);
            }
        }

        // TODO remove it after testing
        System.out.println("Balance Map: " + balanceMap);

        // create maxHeap for amount in negative and minHeap for amount in positive
        PriorityQueue<UserBalance> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.getAmount(), a.getAmount()));
        PriorityQueue<UserBalance> minHeap = new PriorityQueue<>((a, b) -> Double.compare(a.getAmount(), b.getAmount()));

        for (Map.Entry<Long, Double> entry : balanceMap.entrySet()) {
            Long userId = entry.getKey();
            double amount = entry.getValue();

            if (amount < 0) {
                minHeap.offer(new UserBalance(userId, amount));
            } else if (amount > 0) {
                maxHeap.offer(new UserBalance(userId, amount));
            }
        }

        List<Transaction> transactions =  new ArrayList<>();

        while(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            UserBalance debtor = minHeap.poll();
            UserBalance creditor = maxHeap.poll();

            double settleAmount = Math.min(-debtor.getAmount(), creditor.getAmount());

            Transaction t = new Transaction();
            t.setSender(userService.getUserById(debtor.getUserId()));
            t.setReceiver(userService.getUserById(creditor.getUserId()));
            t.setGroup(groupService.getGroupById(groupId));
            t.setDescription("settleUp");
            t.setAmount(settleAmount);

            transactions.add(t);

            debtor.setAmount(debtor.getAmount()+settleAmount);
            creditor.setAmount(creditor.getAmount()-settleAmount);

            if(debtor.getAmount()<0){
                minHeap.offer(debtor);
            }
            if(creditor.getAmount()>0){
                maxHeap.offer(creditor);
            }
        }


       transactionRepository.saveAll(transactions);

        return mapToTransactionResponseDto(transactions);
    }

    private List<TransactionResponseDto> mapToTransactionResponseDto(List<Transaction> transactions){
        List<TransactionResponseDto> transactionResponseDtos = new ArrayList<>();
        for(Transaction t: transactions){
            TransactionResponseDto dto = new TransactionResponseDto();
            dto.setFromUser(t.getSender().getName());
            dto.setToUser(t.getReceiver().getName());
            dto.setAmount(t.getAmount());
            transactionResponseDtos.add(dto);
        }
        return transactionResponseDtos;
    }
}

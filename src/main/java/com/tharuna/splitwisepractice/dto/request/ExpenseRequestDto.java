package com.tharuna.splitwisepractice.dto.request;


import com.tharuna.splitwisepractice.model.User;
import com.tharuna.splitwisepractice.model.constants.PaymentSplitType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExpenseRequestDto {
    private String description;
    private double amount; // 200
    private Long groupId; // Assuming the expense is associated with a group
    private PaymentSplitType paymentSplitType; // Type of payment split (EQUAL, PERCENTAGE, etc.)
    private List<PaymentDto> payments; // [{1:150},{2:50}]
    private List<Long> contributors; // List of user IDs who are contributing to the expense
}

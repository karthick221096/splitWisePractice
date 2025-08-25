package com.tharuna.splitwisepractice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDto {
    private String fromUser;
    private String toUser;
    private double amount;
}

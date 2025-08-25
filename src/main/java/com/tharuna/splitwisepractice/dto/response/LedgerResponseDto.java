package com.tharuna.splitwisepractice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LedgerResponseDto {
    private Long userId;
    private String userName;
    private double amountPaid;
    private double amountOwed;
}

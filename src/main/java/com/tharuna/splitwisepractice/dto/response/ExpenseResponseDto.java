package com.tharuna.splitwisepractice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpenseResponseDto {

    private Long id;
        private String description;
        private double amount;
        private List<LedgerResponseDto> ledgers;

}

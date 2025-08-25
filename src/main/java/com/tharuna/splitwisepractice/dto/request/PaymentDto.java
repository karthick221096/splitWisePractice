package com.tharuna.splitwisepractice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private Long userId; // ID of the user making the payment
    private double amount; // Amount paid by the user
}

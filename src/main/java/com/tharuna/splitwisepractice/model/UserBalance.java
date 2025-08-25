package com.tharuna.splitwisepractice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBalance{
    private Long userId;
    private Double amount;

    public UserBalance(Long userId, double amount){
        this.userId = userId;
        this.amount = amount;
    }
}

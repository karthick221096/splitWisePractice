package com.tharuna.splitwisepractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseClass {
    private String description;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @OneToMany(mappedBy = "expense")
    private List<PaymentLedger> paymentLedgers;
}


/*
expense group
1        1
m        1

expense paymentLedge
1        m
1        1
 */

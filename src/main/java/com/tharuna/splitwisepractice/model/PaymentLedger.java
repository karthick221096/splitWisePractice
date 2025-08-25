package com.tharuna.splitwisepractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"expense_id", "user_id"})
        }
)
public class PaymentLedger extends BaseClass{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;
    private double amountPaid;
    private double amountOwed;

}

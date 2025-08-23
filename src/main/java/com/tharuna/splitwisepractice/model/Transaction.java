package com.tharuna.splitwisepractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Transaction extends BaseClass{
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;
    private double amount;
    private String description;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}

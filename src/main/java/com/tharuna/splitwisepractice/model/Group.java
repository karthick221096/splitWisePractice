package com.tharuna.splitwisepractice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_groups")
public class Group extends BaseClass{
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
    @ManyToMany
    private List<User> members;
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
    @OneToMany(mappedBy = "group")
    private List<Transaction> transactions;
}
/*
group admin
1       1
admin group
1       M

group expens
1       m
expense group
1        1

group(1) < - > expense(m)


* */

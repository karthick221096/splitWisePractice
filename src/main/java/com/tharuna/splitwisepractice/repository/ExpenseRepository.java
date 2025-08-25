package com.tharuna.splitwisepractice.repository;

import com.tharuna.splitwisepractice.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}

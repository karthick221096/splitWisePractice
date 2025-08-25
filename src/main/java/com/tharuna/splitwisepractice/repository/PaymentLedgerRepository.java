package com.tharuna.splitwisepractice.repository;

import com.tharuna.splitwisepractice.model.PaymentLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLedgerRepository extends JpaRepository<PaymentLedger,Long> {
}

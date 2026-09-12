package com.example.transactionservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionservice.Model.Transaction;

@Repository 
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    
}

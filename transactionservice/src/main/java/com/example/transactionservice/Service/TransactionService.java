package com.example.transactionservice.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.transactionservice.DTO.TransactionDTO;
import com.example.transactionservice.Model.Transaction;
import com.example.transactionservice.Repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j 
public class TransactionService {

    private final TransactionRepository repo;
    
    public TransactionService(TransactionRepository repo){
        this.repo=repo;
    }

    //Creating transaction after validation using the model ai
    @Transactional 
    public void insertTransaction(TransactionDTO transactionDTO){
        Transaction transaction = transactionDTO.dtoToModel(transactionDTO);
        log.info("the transaction ID :"+transaction.getId());
        repo.save(transaction);
        log.info(String.format("transaction saved : %s", transaction.getTrancationDate().toString()));

    }

}

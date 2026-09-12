package com.example.transactionservice.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.example.transactionservice.DTO.TransactionDTO;
import com.example.transactionservice.Model.Transaction;
import com.example.transactionservice.Service.TransactionService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j 
public class TransactionController {
    
    private final TransactionService service;
    private final RestClient restClient;
    public TransactionController(TransactionService service){
        this.service=service;
        this.restClient=RestClient.builder().baseUrl("http://localhost:8084").build();

    }

    @GetMapping 
    public String test(){
        return "test The GAtEWAY";
    }

    @PostMapping 
    public ResponseEntity<String> insertAndValidateTransaction(@RequestBody TransactionDTO dto){
        /*RiskEvalution riskEvalution = restClient.post()
            .uri("/api/evalute")
            .body(dto.dtoToModel(dto))
            .retrieve()
            .body(RiskEvalution.class);*/
        service.insertTransaction(dto);
        System.out.println(dto.toString());
        if (dto.isValide()) {
            log.info(String.format("this transaction with id : %s is not valid",dto.getID() ));
        }


        return  ResponseEntity.ok("the transaction is valide");
    }

    private record RiskEvalution(boolean isValid,double riskScore) {
    }
    
}

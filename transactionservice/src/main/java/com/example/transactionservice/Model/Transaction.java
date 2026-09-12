package com.example.transactionservice.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "tranction_table")
@NoArgsConstructor
@AllArgsConstructor 
@Data
@Builder 
public class Transaction {


    @Id
    private String Id;
    @NonNull
    private String accountId;// we can integrate with star-scema and use multi-dem cube in the future
    private String merchentId;
    private BigDecimal amount;
    private LocalDate trancationDate;
    private boolean isValide;
    private double riskScore;

}

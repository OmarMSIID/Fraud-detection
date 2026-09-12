package com.example.transactionservice.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.transactionservice.Model.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    public TransactionDTO(String accountId, String mercentId, BigDecimal amount, boolean isValide, double riskScore) {
        this.accountId = accountId;
        this.mercentId = mercentId;
        this.amount = amount;
        this.isValide = isValide;
        this.riskScore = riskScore;
    }
    @Builder.Default
    private String iD=UUID.randomUUID().toString();
    private String accountId;
    private String mercentId;
    private BigDecimal amount;
    @Builder .Default
    private LocalDate trancationDate=LocalDate.now();
    @JsonProperty(namespace = "isValide")
    private boolean isValide;
    private double riskScore;

    public Transaction dtoToModel(TransactionDTO transactionDTO) {
        return Transaction.builder().Id(transactionDTO.iD).accountId(transactionDTO.accountId)
                        .merchentId(transactionDTO.mercentId).amount(transactionDTO.amount)
                        .trancationDate(transactionDTO.trancationDate).isValide(transactionDTO.isValide)
                        .riskScore(transactionDTO.riskScore).build();
    }

    public TransactionDTO modelToDto(Transaction transaction) {
        return new TransactionDTO(transaction.getId(), transaction.getAccountId(),
                transaction.getMerchentId(), transaction.getAmount(),
                transaction.getTrancationDate(), transaction.isValide(),
                transaction.getRiskScore());
    }

}

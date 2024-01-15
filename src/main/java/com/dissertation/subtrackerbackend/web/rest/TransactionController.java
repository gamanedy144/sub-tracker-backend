package com.dissertation.subtrackerbackend.web.rest;

import com.dissertation.subtrackerbackend.domain.dto.TransactionDTO;
import com.dissertation.subtrackerbackend.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> fetchAllSubscriptions(){
        List<TransactionDTO> transactions = transactionService.findAllDtosForCurrentUser();
        return ResponseEntity.ok(transactions);
    }
}

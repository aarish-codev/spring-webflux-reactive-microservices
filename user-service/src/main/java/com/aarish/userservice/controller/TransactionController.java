package com.aarish.userservice.controller;

import com.aarish.userservice.dto.TransactionRequestDto;
import com.aarish.userservice.dto.TransactionResponseDto;
import com.aarish.userservice.entity.UserTransaction;
import com.aarish.userservice.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono) {
        return requestDtoMono.flatMap(this.transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId) {
        return this.transactionService.getByUserId(userId);
    }
}

package com.example.simelet_assignment_backend.ui.controller;

import com.example.simelet_assignment_backend.service.iservice.IServiceTransactions;
import com.example.simelet_assignment_backend.shared.dto.TransactionsDTO;
import com.example.simelet_assignment_backend.ui.model.request.TransactionsRequest;
import com.example.simelet_assignment_backend.ui.model.response.TransactionsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final IServiceTransactions iServiceTransactions;

    public TransactionsController(IServiceTransactions iServiceTransactions) {
        this.iServiceTransactions = iServiceTransactions;
    }

    //Get All Transactions
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionsResponse> getAllTransactions(){
        ModelMapper mapper = new ModelMapper();

        List<TransactionsResponse> value = new ArrayList<>();
        List<TransactionsDTO> transactionsDTO = iServiceTransactions.getAllTransactions();

        for (TransactionsDTO dto : transactionsDTO){
            value.add(mapper.map(dto, TransactionsResponse.class));
        }

        return value;
    }
    //Post Transactions
    @PostMapping(path = "{/cardId}", consumes = {MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionsResponse postNewTransactions(@PathVariable String cardId,
                                                    @RequestBody TransactionsRequest transactionsRequest){
        ModelMapper mapper = new ModelMapper();
        TransactionsDTO transactionsDTO = mapper.map(transactionsRequest, TransactionsDTO.class);
        TransactionsDTO storedValue = iServiceTransactions.postNewTransactions(cardId, transactionsDTO);

        return null;
    }

}

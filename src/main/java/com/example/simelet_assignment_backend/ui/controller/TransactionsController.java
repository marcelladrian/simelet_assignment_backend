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
@RequestMapping("/api/v1/transactions")
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
    //Post Transactions by card id
    @PostMapping(path = "/{cardid}", consumes = {MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionsResponse postNewTransactions(@PathVariable String cardid,
                                                    @RequestBody TransactionsRequest transactionsRequest){
        ModelMapper mapper = new ModelMapper();
        TransactionsDTO transactionsDTO = mapper.map(transactionsRequest, TransactionsDTO.class);
        TransactionsDTO storedValue = iServiceTransactions.postNewTransactions(cardid, transactionsDTO);

        TransactionsResponse value = mapper.map(storedValue, TransactionsResponse.class);
        return value;
    }
    //Get Transactions by card id
    @GetMapping(path = "/byCard/{cardid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionsResponse> getAllTransactionsByCardId(@PathVariable String cardid){
        ModelMapper mapper = new ModelMapper();
        List<TransactionsResponse> value = new ArrayList<>();

        List<TransactionsDTO> allTransactions = iServiceTransactions.getAllTransactionsByCardId(cardid);

        for (TransactionsDTO dto : allTransactions){
            value.add(mapper.map(dto, TransactionsResponse.class));
        }
        return value;
    }
    //Delete Transactions
    @DeleteMapping(path = "/{cardid}/{transactionsid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransactionsResponse deleteTransactions(@PathVariable String cardid, @PathVariable String transactionsid){
        ModelMapper mapper = new ModelMapper();

        TransactionsDTO transactionsDTO = iServiceTransactions.deleteTransactions(cardid, transactionsid);

        return mapper.map(transactionsDTO, TransactionsResponse.class);
    }
//    Get Transactions by user id
    @GetMapping(path = "/byUser/{userid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TransactionsResponse> getAllTransactionsByUserId(@PathVariable String userid){
        ModelMapper mapper = new ModelMapper();
        List<TransactionsResponse> value = new ArrayList<>();

        List<TransactionsDTO> allTransactions = iServiceTransactions.getAllTransactionsByUserId(userid);

        for (TransactionsDTO dto : allTransactions){
            value.add(mapper.map(dto, TransactionsResponse.class));
        }
        return value;
    }


}

package com.example.simelet_assignment_backend.ui.controller;

import com.example.simelet_assignment_backend.service.iservice.IBalanceInterface;
import com.example.simelet_assignment_backend.shared.dto.BalanceDTO;
import com.example.simelet_assignment_backend.ui.model.request.BalanceRequest;
import com.example.simelet_assignment_backend.ui.model.response.BalanceResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/balances")
public class BalanceController {
    @Autowired
    IBalanceInterface iBalanceInterface;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BalanceResponse addNewBalance(@RequestBody BalanceRequest balanceRequest){
        ModelMapper mapper = new ModelMapper();
        BalanceDTO balanceDTO = mapper.map(balanceRequest, BalanceDTO.class);

        BalanceDTO createdValue = iBalanceInterface.addNewBalance(balanceDTO);

        return mapper.map(createdValue, BalanceResponse.class);
    }

    @PutMapping(path = "/{balanceId}",
            produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BalanceResponse updateBalance(@PathVariable String balanceId, @RequestBody BalanceRequest balanceRequest){
        ModelMapper mapper = new ModelMapper();
        BalanceDTO balanceDTO = mapper.map(balanceRequest, BalanceDTO.class);

        BalanceDTO updatedValue = iBalanceInterface.updateBalance(balanceId, balanceDTO);

        return mapper.map(updatedValue, BalanceResponse.class);
    }

    @GetMapping(path = "/{balanceId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BalanceResponse getBalanceByBalanceId(@PathVariable String balanceId){
        ModelMapper mapper = new ModelMapper();

        BalanceDTO value = iBalanceInterface.getBalanceByBalanceId(balanceId);

        return mapper.map(value, BalanceResponse.class);
    }
}

package com.example.simelet_assignment_backend.shared.dto;

import java.io.Serializable;
import java.util.List;

public class BalanceDTO implements Serializable {
    private static final long serialVersionUID = -3345435373187463119L;
    private String balanceId;
    private long balance;
    private String password;
    private String barcode;

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}

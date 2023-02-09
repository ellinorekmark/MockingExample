package com.example;

public class BankServiceSpy implements BankService {
    private int count = 0;

    @Override
    public void pay(String id, double amount) {
        count++;

    }

    public int getCount() {
        return count;
    }
}

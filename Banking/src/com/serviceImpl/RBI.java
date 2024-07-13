package com.serviceImpl;

public interface RBI {
    void createAccount() throws Throwable;
    void viewDetails() throws Throwable;
    void depositMoney() throws Throwable;
    void withdrawMoney() throws Throwable;
    void viewBalance() throws Throwable;
    void updateDetails() throws Throwable;
}

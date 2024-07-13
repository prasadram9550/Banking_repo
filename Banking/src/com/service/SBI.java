package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.admin.DBUtil;
import com.model.Account;
import com.serviceImpl.RBI;

public class SBI implements RBI {
    static Scanner sc = new Scanner(System.in);

    @Override
    public void createAccount() throws Throwable {
        Account account = new Account();

        System.out.println("Enter All Details of Account Holder ");
        System.out.println("1) Account No :=");
        account.setAccountNo(sc.nextLong());
        sc.nextLine();  // Consume the newline

        System.out.println("2) Holder Name :=");
        account.setHolderName(sc.nextLine());

        System.out.println("3) Address :=");
        account.setAddress(sc.nextLine());

        System.out.println("4) Mobile no :=");
        account.setMobileNo(sc.nextLong());
        sc.nextLine();  // Consume the newline

        System.out.println("5) Aadhar no :=");
        account.setAadharNo(sc.nextLong());
        sc.nextLine();  // Consume the newline

        System.out.println("6) PAN Card No :=");
        account.setPanNo(sc.nextLine());

        System.out.println("7) Gender :=");
        account.setGender(sc.nextLine());

        System.out.println("8) Email Id :=");
        account.setEmailId(sc.nextLine());

        System.out.println("9) Balance :=");
        account.setBalance(sc.nextDouble());

        String insert = String.format("INSERT INTO account (accountNo, holderName, address, mobileNo, aadharNo, panNo, gender, emailId, balance) " +
                "VALUES (%d, '%s', '%s', %d, %d, '%s', '%s', '%s', %.2f)",
                account.getAccountNo(), account.getHolderName(), account.getAddress(), account.getMobileNo(),
                account.getAadharNo(), account.getPanNo(), account.getGender(), account.getEmailId(), account.getBalance());

        try {
            DBUtil.getStatement().execute(insert);
            System.out.println("Account Created Successfully.......");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void viewDetails() throws Throwable {
        System.out.println("Enter Account Number to View Details: ");
        long accountNo = sc.nextLong();

        String query = String.format("SELECT * FROM account WHERE accountNo = %d", accountNo);

        try {
            ResultSet rs = DBUtil.getStatement().executeQuery(query);

            if (rs.next()) {
                Account account = new Account();
                account.setAccountNo(rs.getLong("accountNo"));
                account.setHolderName(rs.getString("holderName"));
                account.setAddress(rs.getString("address"));
                account.setMobileNo(rs.getLong("mobileNo"));
                account.setAadharNo(rs.getLong("aadharNo"));
                account.setPanNo(rs.getString("panNo"));
                account.setGender(rs.getString("gender"));
                account.setEmailId(rs.getString("emailId"));
                account.setBalance(rs.getDouble("balance"));

                System.out.println("Account Details:");
                System.out.println(account);
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void depositMoney() throws Throwable {
        System.out.println("Enter Account Number: ");
        long accountNo = sc.nextLong();

        System.out.println("Enter Amount to Deposit: ");
        double depositAmount = sc.nextDouble();

        String query = String.format("SELECT balance FROM account WHERE accountNo = %d", accountNo);

        try {
            ResultSet rs = DBUtil.getStatement().executeQuery(query);

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                double newBalance = currentBalance + depositAmount;

                String update = String.format("UPDATE account SET balance = %.2f WHERE accountNo = %d", newBalance, accountNo);
                DBUtil.getStatement().execute(update);
                
                System.out.println("Money deposited successfully. New balance: " + newBalance);
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void withdrawMoney() throws Throwable {
        System.out.println("Enter Account Number: ");
        long accountNo = sc.nextLong();

        System.out.println("Enter Amount to Withdraw: ");
        double withdrawAmount = sc.nextDouble();

        String query = String.format("SELECT balance FROM account WHERE accountNo = %d", accountNo);

        try {
            ResultSet rs = DBUtil.getStatement().executeQuery(query);

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                if (currentBalance >= withdrawAmount) {
                    double newBalance = currentBalance - withdrawAmount;

                    String update = String.format("UPDATE account SET balance = %.2f WHERE accountNo = %d", newBalance, accountNo);
                    DBUtil.getStatement().execute(update);

                    System.out.println("Money withdrawn successfully. New balance: " + newBalance);
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void viewBalance() throws Throwable {
        System.out.println("Enter Account Number to View Balance: ");
        long accountNo = sc.nextLong();

        String query = String.format("SELECT balance FROM account WHERE accountNo = %d", accountNo);

        try {
            ResultSet rs = DBUtil.getStatement().executeQuery(query);

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                System.out.println("Current balance: " + balance);
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement();
            DBUtil.closeConnection();
        }
    }

    @Override
    public void updateDetails() throws Throwable {
        System.out.println("Enter Account Number to Update Details: ");
        long accountNo = sc.nextLong();
        sc.nextLine(); // Consume newline

        String query = String.format("SELECT * FROM account WHERE accountNo = %d", accountNo);

        try {
            ResultSet rs = DBUtil.getStatement().executeQuery(query);

            if (rs.next()) {
                Account account = new Account();
                account.setAccountNo(rs.getLong("accountNo"));
                account.setHolderName(rs.getString("holderName"));
                account.setAddress(rs.getString("address"));
                account.setMobileNo(rs.getLong("mobileNo"));
                account.setAadharNo(rs.getLong("aadharNo"));
                account.setPanNo(rs.getString("panNo"));
                account.setGender(rs.getString("gender"));
                account.setEmailId(rs.getString("emailId"));
                account.setBalance(rs.getDouble("balance"));

                System.out.println("Current Details:");
                System.out.println(account);

                // Prompt user for updated details
                System.out.println("Enter Updated Details:");

                System.out.println("Holder Name (Enter to keep current: " + account.getHolderName() + "):");
                String holderName = sc.nextLine().trim();
                if (!holderName.isEmpty()) {
                    account.setHolderName(holderName);
                }

                System.out.println("Address (Enter to keep current: " + account.getAddress() + "):");
                String address = sc.nextLine().trim();
                if (!address.isEmpty()) {
                    account.setAddress(address);
                }

                System.out.println("Mobile Number (Enter to keep current: " + account.getMobileNo() + "):");
                String mobileNoStr = sc.nextLine().trim();
                if (!mobileNoStr.isEmpty()) {
                    long mobileNo = Long.parseLong(mobileNoStr);
                    account.setMobileNo(mobileNo);
                }

                System.out.println("Aadhar Number (Enter to keep current: " + account.getAadharNo() + "):");
                String aadharNoStr = sc.nextLine().trim();
                if (!aadharNoStr.isEmpty()) {
                    long aadharNo = Long.parseLong(aadharNoStr);
                    account.setAadharNo(aadharNo);
                }

                System.out.println("PAN Card Number (Enter to keep current: " + account.getPanNo() + "):");
                String panNo = sc.nextLine().trim();
                if (!panNo.isEmpty()) {
                    account.setPanNo(panNo);
                }

                System.out.println("Gender (Enter to keep current: " + account.getGender() + "):");
                String gender = sc.nextLine().trim();
                if (!gender.isEmpty()) {
                    account.setGender(gender);
                }

                System.out.println("Email ID (Enter to keep current: " + account.getEmailId() + "):");
                String emailId = sc.nextLine().trim();
                if (!emailId.isEmpty()) {
                    account.setEmailId(emailId);
                }

                // Update details in the database
                String updateQuery = String.format("UPDATE account SET holderName = '%s', address = '%s', " +
                                "mobileNo = %d, aadharNo = %d, panNo = '%s', gender = '%s', emailId = '%s' WHERE accountNo = %d",
                        account.getHolderName(), account.getAddress(), account.getMobileNo(), account.getAadharNo(),
                        account.getPanNo(), account.getGender(), account.getEmailId(), account.getAccountNo());

                DBUtil.getStatement().executeUpdate(updateQuery);
                System.out.println("Account details updated successfully.");

            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement();
            DBUtil.closeConnection();
        }
    }
}

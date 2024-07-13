package a_test;


import java.util.Scanner;

import com.service.SBI;
import com.serviceImpl.RBI;

public class AdminTest {
    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        RBI sbi = new SBI();

        while (true) {
            System.out.println("Banking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. View Balance");
            System.out.println("6. Update Account Details");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sbi.createAccount();
                    break;
                case 2:
                    sbi.viewDetails();
                    break;
                case 3:
                    sbi.depositMoney();
                    break;
                case 4:
                    sbi.withdrawMoney();
                    break;
                case 5:
                    sbi.viewBalance();
                    break;
                case 6:
                    sbi.updateDetails();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

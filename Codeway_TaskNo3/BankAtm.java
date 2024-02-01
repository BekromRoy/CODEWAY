import java.util.Scanner;

// BankAccount class to represent the user's bank account
class BankAccount {
    public double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
    }
}

// ATM class to represent the ATM machine
class ATM {
    public BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                System.out.println();
                withdraw();
                break;
            case 2:
                System.out.println();
                deposit();
                break;
            case 3:
                System.out.println();
                checkBalance();
                break;
            case 4:
                System.out.println();
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            if (bankAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. Remaining balance: $" + bankAccount.getBalance());
            }
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the deposit amount: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            bankAccount.deposit(amount);
            System.out.println("Deposit successful. New balance: $" + bankAccount.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + bankAccount.getBalance());
    }
}

public class BankAtm {
    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(1000.0); // Create a bank account with an initial balance

        ATM atm = new ATM(userAccount); // Create an ATM with the user's bank account

        // ATM interaction loop
        while (true) {
            atm.displayMenu();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            atm.processOption(choice);
        }
    }
}

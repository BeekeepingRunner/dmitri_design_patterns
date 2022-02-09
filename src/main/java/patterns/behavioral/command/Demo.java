package patterns.behavioral.command;

import java.util.List;

class BankAccount {

    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
    }

    public void withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {
    void call();
}

class BankAccountCommand implements Command
{
    private BankAccount account;

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT -> account.deposit(amount);
            case WITHDRAW -> account.withdraw(amount);
        }
    }

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }
}

public class Demo {

    public static void main(String[] args) {

        final BankAccount ba = new BankAccount();
        System.out.println(ba);

        final List<BankAccountCommand> commands = List.of(
                new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000)
        );

        for (BankAccountCommand c : commands) {
            c.call();
            System.out.println(ba);
        }
    }
}

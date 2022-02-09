package patterns.behavioral.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankAccount {

    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
            return true;
        } else
            return false;
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
    void undo();
}

class BankAccountCommand implements Command
{
    private BankAccount account;
    private boolean succeeded;

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT -> {
                account.deposit(amount);
                succeeded = true;
            }
            case WITHDRAW -> {
                succeeded = account.withdraw(amount);
            }
        }
    }

    @Override
    public void undo() {
        if (!succeeded)
            return;

        switch (action) {
            case DEPOSIT -> account.withdraw(amount);
            case WITHDRAW -> account.deposit(amount);
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

        BankAccount ba = new BankAccount();
        System.out.println(ba);

        List<BankAccountCommand> commands = new ArrayList<>(List.of(
                new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000)
        ));

        for (Command c : commands) {
            c.call();
            System.out.println(ba);
        }

        Collections.reverse(commands);
        for (Command c : commands) {
            c.undo();
            System.out.println(ba);
        }
    }
}

package miniquizzes._3._2;

public class Account {
    private String owner;
    private int balance;

    public Account(String o, int b) {
        owner = o;
        balance = b;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void transferTo(Account other, int amount) {
        this.balance -= amount;
        other.balance += amount;
    }

    public String toString() {
        return owner + ": $" + balance;
    }

}
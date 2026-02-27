package miniquizzes._3._2;

public class _2 {
    public static void main(String[] args) {
        // PART 1: What does this code print?
        Account a = new Account("Alice", 100);
        Account b = new Account("Bob", 50);
        a.transferTo(b, 30);
        System.out.println(a);
        System.out.println(b);

        // PART 2: What does this code print?
        Account x = new Account("X", 200);
        Account y = x;
        y.deposit(50);
        System.out.println(x.getBalance());
    }
}

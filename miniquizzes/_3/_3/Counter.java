package miniquizzes._3._3;

public class Counter {
    private static int total = 0;
    public static final int MAX = 50;
    private int value;

    public Counter(int v) {
        value = v;
        total++;
    }

    public static int getTotal() {
        return total;
    }

    public int getValue() {
        return value;
    }

}

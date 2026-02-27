package miniquizzes._1;

public class _4 {
    private int x;
    private double y;

    public _4() {
        x = (int) (Math.random() * 4) + 1;
        y = Math.random();
    }
    public void increaseY() {
        y += 1;
    }
    public static void main(String[] args) {

        _4 o = new _4(); 

        o.increaseY();


        // PART 1: Create an instance of the Object class
        // PART 2: Call the increaseY() instance method on that instance
        // PART 3: What will the values of the x and y instance variables be?

        // YOUR CODE HERE
    } 
}

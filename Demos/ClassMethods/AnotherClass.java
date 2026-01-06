package Demos.ClassMethods;

public class AnotherClass {

    public static void iAmAPublicClassMethod() {
        System.out.println("HIIIIIIII :)");
        // butYouCanCallMe();
    }

    private static void youCantCallMe() {
        System.out.println("HELLO!?!?!?!");
    }

    private static void butYouCanCallMe() {
        System.out.println("Hi Again.");
    }
}

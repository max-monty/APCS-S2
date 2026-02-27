package miniquizzes._4._1;

public class _1 {
    public static void main(String[] args) {

        // PART 1: What are the default values?
        // int[] nums = new int[3];
        // double[] vals = new double[2];
        // boolean[] flags = new boolean[2];
        // String[] words = new String[2];
        //
        // System.out.println(nums[0]);
        // System.out.println(vals[0]);
        // System.out.println(flags[0]);
        // System.out.println(words[0]);

        // PART 2: What does this code print?
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println(arr[0]);
        System.out.println(arr[arr.length - 1]);
        System.out.println(arr.length);

        // PART 3: What does this code print?
        int[] nums = {5, 10, 15, 20};
        nums[1] = 99;
        int temp = nums[0];
        nums[0] = nums[3];
        nums[3] = temp;
        // What is the array now?
        // What does nums[0] + nums[1] evaluate to?

        // PART 4: What does this code print?
        // int[] a = {1, 2, 3};
        // int[] b = a;
        // b[0] = 99;
        // System.out.println(a[0]);

        // PART 5: Will this compile and run? If not, what error?
        // int[] x = new int[3];
        // System.out.println(x[3]);

    }
}

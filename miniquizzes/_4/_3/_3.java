package miniquizzes._4._3;

public class _3 {
    public static void main(String[] args) {

        int[] nums = {4, 7, 2, 9, 1, 5};

        // PART 1: What does this code compute? What is the final value of max?
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // PART 2: What does this code compute? What is the final value of result?
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        double result = (double) sum / nums.length;

        // PART 3: What is the value of count after this runs?
        // int count = 0;
        // for (int num : nums) {
        //     if (num % 2 == 0) {
        //         count++;
        //     }
        // }

        // PART 4: What is the value of allPos after this runs?
        // boolean allPos = true;
        // for (int num : nums) {
        //     if (num <= 0) {
        //         allPos = false;
        //     }
        // }

        // PART 5: Write code to find the INDEX of the value 9 in nums
        // using a for loop. Store the result in a variable called idx.
        // If the value is not found, idx should be -1.

    }
}

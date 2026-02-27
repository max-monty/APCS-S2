package miniquizzes._2;

public class _4 {
    public static void main(String[] args) {
        int[] nums = { 4, 7, 2, 9, 1, 5 };

        // PART 1: What does this code find? What is the final value of result?
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > result) {
                result = nums[i];
            }
        }
        // PART 2: What does this code compute?
        int total = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 3) {
                total += nums[i];
                count++;
            }
        }
        double answer = (double) total / count;

        // PART 3: Write code to check if ANY element in nums is even
        // Store result in boolean hasEven

    }
}

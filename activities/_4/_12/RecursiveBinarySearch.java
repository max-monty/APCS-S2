package activities._4._12;

public class RecursiveBinarySearch {
    public static void main(String[] args) {

        int[] sorted = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};

        // test your method here
        System.out.println(search(sorted, 14));   // expected: 6
        // System.out.println(search(sorted, 5));    // expected: -1

    }

    // RECURSIVE binary search.
    //   Precondition: arr is sorted in ascending order.
    //   Return the index of target, or -1 if target is not in arr.
    //
    //   Think about:
    //     - What are the base cases?
    //     - How do you compute mid from lo and hi?
    //     - Which half do you recurse on, and how do you shrink the window?
    public static int binarySearch(int[] arr, int target, int lo, int hi) {
        return -1;
    }

    // Wrapper method — leave this alone. It lets callers write search(arr, target)
    // without worrying about lo and hi.
    public static int search(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }
}

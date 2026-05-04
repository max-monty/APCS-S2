package demos._4._12;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Array: " + Arrays.toString(sorted));

        System.out.println("Find 7  -> index " + search(sorted, 7));    // 3
        System.out.println("Find 19 -> index " + search(sorted, 19));   // 9
        System.out.println("Find 4  -> index " + search(sorted, 4));    // -1
    }

    // RECURSIVE binary search.
    //   BASE CASE 1: lo > hi  -> empty window, return -1
    //   BASE CASE 2: arr[mid] == target -> found it, return mid
    //   RECURSIVE CALL: search left or right half, never both
    public static int binarySearch(int[] arr, int target, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (lo + hi) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, hi);   // search right half
        }
        return binarySearch(arr, target, lo, mid - 1);       // search left half
    }

    // Wrapper so callers don't need to pass lo and hi.
    public static int search(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }
}

package demos._4._12;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Array: " + Arrays.toString(sorted));

        // === ITERATIVE BINARY SEARCH (for comparison) ===
        System.out.println("\n=== ITERATIVE ===");
        System.out.println("Find 7  -> index " + iterativeSearch(sorted, 7));    // 3
        System.out.println("Find 19 -> index " + iterativeSearch(sorted, 19));   // 9
        System.out.println("Find 4  -> index " + iterativeSearch(sorted, 4));    // -1

        // === RECURSIVE BINARY SEARCH ===
        System.out.println("\n=== RECURSIVE ===");
        System.out.println("Find 7  -> index " + binarySearch(sorted, 7, 0, sorted.length - 1));   // 3
        System.out.println("Find 19 -> index " + binarySearch(sorted, 19, 0, sorted.length - 1));  // 9
        System.out.println("Find 4  -> index " + binarySearch(sorted, 4, 0, sorted.length - 1));   // -1

        // === WRAPPER METHOD (cleaner API) ===
        // Callers don't have to know about lo and hi.
        System.out.println("\n=== WRAPPER ===");
        System.out.println("Find 11 -> index " + search(sorted, 11));   // 5
        System.out.println("Find 0  -> index " + search(sorted, 0));    // -1

        // === TRACE ===
        System.out.println("\n=== TRACE Find 15 ===");
        searchTrace(sorted, 15, 0, sorted.length - 1, 0);
    }

    // ITERATIVE binary search using a while loop (for comparison).
    public static int iterativeSearch(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    // RECURSIVE binary search.
    //   Precondition: arr is sorted ascending.
    //   lo and hi are the inclusive bounds of the region we are still searching.
    //
    //   BASE CASE 1: lo > hi           -> target is not in the array, return -1
    //   BASE CASE 2: arr[mid] == target-> found it, return mid
    //   RECURSIVE CALL: search either the LEFT half or the RIGHT half,
    //                   not both (that's why binary search is fast).
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

    // Wrapper: the caller just passes the array and target.
    // The wrapper fills in the initial lo/hi and kicks off the recursion.
    public static int search(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }

    // Same as binarySearch but prints an indented trace so students can see
    // how each call narrows the search window.
    public static int searchTrace(int[] arr, int target, int lo, int hi, int depth) {
        String pad = "  ".repeat(depth);
        if (lo > hi) {
            System.out.println(pad + "-> lo=" + lo + " hi=" + hi + " (empty window) returns -1");
            return -1;
        }
        int mid = (lo + hi) / 2;
        System.out.println(pad + "-> lo=" + lo + " hi=" + hi
            + "  mid=" + mid + "  arr[mid]=" + arr[mid]);
        if (arr[mid] == target) {
            System.out.println(pad + "<- found, returns " + mid);
            return mid;
        }
        int result;
        if (arr[mid] < target) {
            result = searchTrace(arr, target, mid + 1, hi, depth + 1);
        } else {
            result = searchTrace(arr, target, lo, mid - 1, depth + 1);
        }
        System.out.println(pad + "<- returns " + result);
        return result;
    }
}

package demos._4._12;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 1, 9, 2, 7, 4};
        System.out.println("Before: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("After:  " + Arrays.toString(arr));
    }

    // RECURSIVE merge sort.
    //   BASE CASE: lo >= hi -> a single element (or empty) is already sorted
    //   RECURSIVE CALLS: sort the left half, sort the right half
    //   COMBINE: merge the two sorted halves back together
    public static void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;                         // base case: 0 or 1 elements
        }
        int mid = (lo + hi) / 2;
        mergeSort(arr, lo, mid);            // sort left half
        mergeSort(arr, mid + 1, hi);        // sort right half
        merge(arr, lo, mid, hi);            // merge the two sorted halves
    }

    // Merge arr[lo..mid] and arr[mid+1..hi] into sorted order.
    public static void merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int i = lo;         // pointer into left half
        int j = mid + 1;    // pointer into right half
        int k = 0;          // pointer into temp

        // Compare elements from each half, take the smaller one
        while (i <= mid && j <= hi) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy any remaining elements from the left half
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy any remaining elements from the right half
        while (j <= hi) {
            temp[k++] = arr[j++];
        }

        // Copy temp back into the original array
        for (int x = 0; x < temp.length; x++) {
            arr[lo + x] = temp[x];
        }
    }
}

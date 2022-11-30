package advance;

import java.util.Arrays;

public class Day47Sorting {

  /**
   * Sort an array using insertion sort
   * 
   * @param A
   * @param N
   */
  public static int[] insertionSort(int[] A, int N) {
    for (int i = 1; i < N; i++) {
      int temp = A[i];
      int j = i - 1;
      while (j >= 0 && A[j] > temp) {
        A[j + 1] = A[j];
        j--;
      }
      A[j + 1] = temp;
    }
    return A;
  }

  /**
   * Quick sort algorithm
   * 
   * @param A
   * @param l
   * @param h
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*logN) - Ideal | O(N*N) - Worst
   *         Space: O(N)
   * 
   *         NOTE: Use quick sort incase we want space optimization
   *         Use Merge sort for time complexity in every case
   */
  public static int[] quickSort(int[] A, int l, int h) {
    if (l < h) {
      int part = partition(A, l, h);
      quickSort(A, l, part - 1);
      quickSort(A, part + 1, h);
    }
    return A;
  }

  /* Partition algorithm for Quick sort */
  public static int partition(int[] A, int l, int h) {
    int pivot = A[h];
    // Index is move smaller elements on left of "pivot"
    int i = l;
    for (int j = l; j <= h - 1; j++) {
      if (A[j] < pivot) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        i++;
      }
    }
    // IMP: Swap is needed to move the pivot as last element was considered to be
    // pivot
    int temp = A[i];
    A[i] = A[h];
    A[h] = temp;
    return i;
  }

  /**
   * Min. of increments to make the array of unique elements
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*logN)
   *         Space: O(1)
   */
  public static int minNoOfIncrements(int[] A, int N) {
    int ans = 0;
    Arrays.sort(A);
    for (int i = 1; i < N; i++) {
      if (A[i] <= A[i - 1]) {
        int newVal = A[i - 1] + 1;
        ans += A[i - 1] - A[i];
        A[i] = newVal;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    // int[] A = { 3, 10, -1, 2, 4, 6 };
    // System.out.println(Arrays.toString(insertionSort(A, A.length)));

    // int[] A = { 9, 8, 1, 6, 5, 8, 1, 5 };
    // // System.out.println(partition(A, 0, A.length - 1));
    // System.out.println(Arrays.toString(quickSort(A, 0, A.length - 1)));

    // int[] A = { 3, 2, 1, 2, 1, 7 }; // 6
    int[] A = { 1, 2, 1 }; // 6
    System.out.println(minNoOfIncrements(A, A.length));
  }
}

package advance;

import java.util.Arrays;

public class Day46Sorting {

  /**
   * Selection sort algorithm
   * 
   * @param A
   * @param N
   * 
   *          Approach is to find the min element and place it at the 0th index
   *          2nd smallest at 1st position and so on
   */
  public static void selectionSort(int[] A, int N) {
    // for (int i = N - 1; i >= 1; i--) {
    // int maxId = 0;
    // for (int j = 1; j <= i; j++) {
    // if (A[j] > A[maxId]) {
    // maxId = j;
    // }
    // }

    // int temp = A[i];
    // A[i] = A[maxId];
    // A[maxId] = temp;
    // }

    // System.out.println(Arrays.toString(A));

    for (int i = 0; i < N - 1; i++) {
      int minIndx = i;
      for (int j = i + 1; j < N; j++) {
        if (A[j] < A[minIndx]) {
          minIndx = j;
        }
      }

      int temp = A[minIndx];
      A[minIndx] = A[i];
      A[i] = temp;
    }

    System.out.println(Arrays.toString(A));
  }

  /**
   * Merge 2 sorted elements
   * 
   * @param A
   * @param B
   * @param N
   * @param M
   */
  public static void mergeSortedArrays(int[] A, int B[], int N, int M) {
    int[] ans = new int[N + M];
    int i = 0, j = 0, k = 0;

    while (i < N && j < M) {
      if (A[i] < B[j]) {
        ans[k++] = A[i++];
      } else {
        ans[k++] = B[j++];
      }
    }

    while (i < N) {
      ans[k++] = A[i++];
    }

    while (j < M) {
      ans[k++] = B[j++];
    }
  }

  /**
   * Merge sort algorithm
   * 
   * @param A
   * @param st
   * @param end
   */
  public static void mergeSort(int[] A, int st, int end) {
    if (st == end) {
      return;
    }

    int mid = (st + end) / 2;
    mergeSort(A, st, mid);
    mergeSort(A, mid + 1, end);
    mergeSortedArrays(A, A, end, mid);
  }

  /**
   * Count the inversion pairs where i < j but A[i] > A[j]
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*N)
   *         Space: O(1)
   */
  public static int inversionPairs(int[] A, int N) {
    int ans = 0;
    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < N; j++) {
        if (A[i] > A[j]) {
          ans++;
        }
      }
    }

    return ans;
  }

  /**
   * Count the inversion pairs where i < j but A[i] > A[j]
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*logN)
   *         Space: O(N)
   */
  public static int inversionPairsBetter(int[] A, int l, int r) {
    return mergeSortAndCount(A, l, r);
  }

  /* Merge sort and count */
  public static int mergeSortAndCount(int[] A, int l, int r) {
    int count = 0;
    if (l < r) {
      int m = (l + r) / 2;
      count += mergeSortAndCount(A, l, m);
      count += mergeSortAndCount(A, m + 1, r);
      count += mergeAndCount(A, l, m, r);
    }
    return count;
  }

  /* Count swaps */
  public static int mergeAndCount(int[] A, int l, int m, int r) {
    int[] left = Arrays.copyOfRange(A, l, m + 1);
    int[] right = Arrays.copyOfRange(A, m + 1, r + 1);
    int i = 0, j = 0, k = l, swaps = 0;

    while (i < left.length && j < right.length) {
      if (left[i] <= right[j])
        A[k++] = left[i++];
      else {
        A[k++] = right[j++];
        swaps += (left.length - i);
      }
    }

    while (i < left.length)
      A[k++] = left[i++];
    while (j < right.length)
      A[k++] = right[j++];

    return swaps;
  }

  public static void main(String[] args) {
    // int[] A = { 10, 9, 1, 2, 3 };
    // selectionSort(A, A.length);

    // int[] A = { 1, 3, 5, 7 };
    // int[] B = { 2, 4, 6, 8 };
    // mergeSortedArrays(A, B, A.length, B.length);

    // int[] A = { 8, 3, 4 }; // 2
    // int[] A = { 4, 5, 1, 6, 3, 2 }; // 7
    // System.out.println(inversionPairs(A, A.length));

    // int[] A = { 1, 20, 6, 4, 5 }; // 5
    // int[] A = { 4, 5, 1, 2, 6, 3 }; // 7
    // int[] A = { 4, 4, 4, 4, 4 }; // 0
    // System.out.println(inversionPairsBetter(A, 0, A.length - 1));
  }
}

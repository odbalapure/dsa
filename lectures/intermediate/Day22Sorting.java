package intermediate;

import java.util.Arrays;

public class Day22Sorting {

  /**
   * Find min. cost of removing all elements from the array
   * NOTE: Cost of removing an element A[i] is sum of all elements present in the
   * array before removing A[i]
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         - Sort array in descending order
   *         - Start from removing the largest element
   *         - Each item gets counted as many times as its index (starting at one)
   *         the first element is counted once, the second twice, etc
   */
  public static int minCostToRemoveElements(int[] A, int N) {
    int ans = 0;

    // Sort array in descending order
    Arrays.sort(A);
    int st = 0, end = N - 1;
    while (st <= end) {
      int temp = A[st];
      A[st] = A[end];
      A[end] = temp;
      st++;
      end--;
    }

    for (int i = 0; i < N; i++) {
      // If cost of removing elemtn was 2^(removed_count) * arr[i]
      // ans += A[i] * Math.pow(2, i);
      ans += A[i] * (i + 1);
    }

    return ans;
  }

  /**
   * Find no. of noble elements
   * Element A[i] is noble if no. of elements less than A[i] are equal to A[i]
   * 
   * @param A
   * @param N
   * @return
   */
  public static int nobleElements(int[] A, int N) {
    /* Brute Force */
    // int ans = 0;
    // for (int i = 0; i < N; i++) {
    // int ct = 0;
    // for (int j = 0; j < N; j++) {
    // if (A[i] < 0) {
    // continue;
    // }

    // if (i == j) {
    // continue;
    // }

    // if (A[i] > A[j]) {
    // ct++;
    // }
    // }

    // if (ct == A[i]) {
    // ans++;
    // }
    // }

    // return ans;

    /* Optimal */
    Arrays.sort(A);
    int ans = 0;

    for (int i = 0; i < N; i++) {
      if (A[i] == i) {
        ans += 1;
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    int[] A = { 3, 5, 2 }; // 17
    System.out.println(minCostToRemoveElements(A, A.length));

    // int[] A = { 1, -5, 3, 5, 5, -10, 4 }; // 4
    // int[] A = { -10, 1, 3, 1, 100 }; // 3
    // int[] A = { 10, 1, 3, -5, 4, -10, 5 }; // 3
    // System.out.println(nobleElements(A, A.length));
  }
}

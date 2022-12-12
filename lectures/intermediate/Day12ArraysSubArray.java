package intermediate;

public class Day12ArraysSubArray {

  /**
   * Find the maximum sum of a subarray
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int maxSubArraySum(int[] A, int N) {
    // int res = Integer.MIN_VALUE;
    // for (int i = 0; i < N; i++) {
    // int curr = 0;
    // for (int j = i; j < N; j++) {
    // curr += A[j];
    // res = Math.max(res, curr);
    // }
    // }
    // return res;

    int sum = 0;
    int res = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      sum += A[i];
      res = Math.max(sum, res);
      sum = sum < 0 ? 0 : sum;
    }
    return res;
  }

  /**
   * Find the start index of subarray (size k) with least average
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int subArrWitMinAverage(int[] A, int N, int k) {
    int ans = 0, curr = 0;
    for (int i = 0; i < k; i++)
      curr += A[i];

    int minSum = curr;
    for (int i = k; i < N; i++) {
      curr += A[i] - A[i - k];
      if (curr < minSum) {
        minSum = curr;
        ans = (i - k + 1);
      }
    }

    return ans;
  }

  /**
   * Sum of all subarrays
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   * 
   * APPROACH
   * 
   */
  public static int sumAllSubArrays(int[] A, int N) {
    long ans = 0;
    for (int i = 0; i < N; i++)
      ans += (long) A[i] * (i + 1) * (N - i);
    return (int) ans;
  }

  public static void main(String[] args) {
    // int[] arr = { 1, 4, 6, 2, 8, 9, 10 }; // 40
    // int[] arr = { 1, 2, 3, 4, -10 }; // 10
    // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 }; // 6
    // System.out.println(maxSubArraySum(arr, arr.length));

    // int[] A = { 3, 7, 90, 20, 10, 50, 40 }; // 3
    // System.out.println(subArrWitMinAverage(A, A.length, 3));
    // int[] A = { 3, 7, 5, 20, -10, 0, 12 }; // 4
    // System.out.println(subArrWitMinAverage(A, A.length, 2));

    // int[] A = { 1, 2, 3 }; // 20
    // int[] A = { 2, 1, 3 }; // 19
    // System.out.println(sumAllSubArrays(A, A.length));
  }
}

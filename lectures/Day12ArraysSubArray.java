package lectures;

public class Day12ArraysSubArray {

  /**
   * Find the maximum sum of a subarray
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*N)
   *         Space: O(1)
   */
  public static int maxSubArraySum(int[] A, int N) {
    int res = 0;
    for (int i = 0; i < N; i++) {
      int curr = 0;
      for (int j = i; j < N; j++) {
        curr += A[j];
        res = Math.max(res, curr);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 4, 6, 2, 8, 9, 10 };
    System.out.println(maxSubArraySum(arr, arr.length)); // 40
  }
}

package intermediate;

public class Day13ArraysInterview {

  /**
   * Print the start and end index of subarrays of size 'K'
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N - K) // 'K' is not constant
   *         Space: O(1)
   */
  public static void startEndIndexSubArray(int[] A, int N, int K) {
    int s = 0, e = K - 1;

    while (e < N) {
      System.out.println("[" + A[s] + ", " + A[e] + "]");
      s++;
      e++;
    }
  }

  /**
   * Find the no. of subarrays of size 'K'
   * 
   * @param A
   * @param N
   * @param K
   * @return
   */
  public static int noOfSubArraysSizeK(int[] A, int N, int K) {
    return N - K + 1;
  }

  /**
   * Find the max sum of a subarray of size 'K'
   * 
   * @param A
   * @param N
   * @param K
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N) [ideal] | O(N*N) [worst]
   *         Space: O(1)
   * 
   *         If K=1 => O(N - 1 + 1) * 1 = O(N)
   *         If k=N => O(N - N + 1) * N = O(N)
   *         If k=N/2 => O(N - N/2 + 1) * N/2 = O(N*N) [Worst time complexity]
   */
  public static int maxSubArraySumSizeK(int[] A, int N, int K) {
    int s = 0, e = K - 1, ans = Integer.MIN_VALUE;
    while (e < N) {
      int sum = 0;
      for (int i = s; i <= e; i++) {
        sum += A[i];
      }

      ans = Math.max(ans, sum);
      s++;
      e++;
    }

    return ans;
  }

  /**
   * Find the max sum of a subarray of size 'K' - Prefix Sum
   * 
   * @param A
   * @param N
   * @param K
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   * 
   *         IF k=1, O (N + N - K + 1) =~ O(2N) =~ O(N)
   */
  public static int maxSubArraySumSizeKBetter(int[] A, int N, int K) {
    int s = 0, e = K - 1, sum = 0, ans = Integer.MIN_VALUE;
    int[] prefix = prefix(A, N);

    while (e < N) {
      if (s == 0) {
        sum = prefix[e];
      } else {
        sum = prefix[e] - prefix[s - 1];
      }

      ans = Math.max(ans, sum);
      s++;
      e++;
    }

    return ans;
  }

  /* Helper function to create a prefix sum array */
  public static int[] prefix(int[] A, int N) {
    int[] prefix = new int[N];
    prefix[0] = A[0];
    for (int i = 1; i < N; i++) {
      prefix[i] = A[i] + prefix[i - 1];
    }

    return prefix;
  }

  /**
   * Find the max sum of a subarray of size 'K' - Sliding Window
   * 
   * @param A
   * @param N
   * @param K
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N) + O(K) =~ ON
   *         Space: O(1)
   */
  public static int maxSubArraySumSizeKBetterSlidingWindow(int[] A, int N, int K) {
    int sum = 0, ans = Integer.MIN_VALUE;
    for (int i = 0; i < K; i++) {
      sum += A[i];
    }

    for (int i = K; i < N; i++) {
      sum += A[i] - A[i - K];
      ans = Math.max(ans, sum);
    }

    return ans;
  }

  public static void main(String[] args) {
    // int[] A = { 3, 4, 2, -1, 6, 7, 8, 9, 3, 2, -1, 4 }; // [3, 2], [4, -1], [2,
    // 6]...
    // startEndIndexSubArray(A, A.length, 3);
    // System.out.println(noOfSubArraysSizeK(A, A.length, 3));

    // int[] A = { -3, 4, -2, 5, 3, -2, 8, 2, -1, 4 }; // 16
    // System.out.println(maxSubArraySumSizeK(A, A.length, 5));
    // System.out.println(maxSubArraySumSizeKBetter(A, A.length, 5));

    // int[] A = { 1, 4, 2, 10, 23, 3, 1, 0, 20 }; // 39
    // System.out.println(maxSubArraySumSizeK(A, A.length, 4));
    // System.out.println(maxSubArraySumSizeKBetter(A, A.length, 4));
    // System.out.println(maxSubArraySumSizeKBetterSlidingWindow(A, A.length, 4));
  }
}

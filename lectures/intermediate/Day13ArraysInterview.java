package intermediate;

import java.util.Arrays;

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

  /**
   * Find the min cost of 3 tress such that A[i] < A[j] < A[k]
   * 
   * APPOACH
   * - Brute force will take O(N^3) if we try every possible triplet
   * - For O(N^2) apporach we take an element A[i] as pivot
   * \_ Find smaller element on left side and update cost -> Math.min(costI,
   * cost[j]);
   * \_ Find bigger element on right side and update cost -> Math.min(costI,
   * cost[j]);
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*N)
   *         Space: O(1)
   */
  public static int minCoschristmasTrees(int[] A, int[] cost, int N) {
    int cost3 = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      int costJ = Integer.MAX_VALUE;
      int costK = Integer.MAX_VALUE;

      for (int j = 0; j < i; j++)
        if (A[j] < A[i])
          costJ = Math.min(costJ, cost[j]);

      for (int k = i + 1; k < N; k++)
        if (A[k] > A[i])
          costK = Math.min(costK, cost[k]);

      cost3 = costJ != Integer.MAX_VALUE && costK != Integer.MAX_VALUE ? Math.min(cost3, cost[i] + costJ + costK)
          : cost3;
    }

    return cost3 == Integer.MAX_VALUE ? -1 : cost3;
  }

  /**
   * Length of max consecutive 1s by removing atmost one '0'
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(3N) =~ O(N)
   *         Space: O(1)
   */
  public static int maxCons1sRemove0(String A, int N) {
    int ans = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      if (A.charAt(i) == '0') {
        int lc = 0, rc = 0;
        // Find count of 1s on left
        for (int j = 0; j < i; j++) {
          if (A.charAt(j) == '1')
            lc++;
          else
            break;
        }
        // Find count of 1s on right
        for (int j = i + 1; j < N; j++) {
          if (A.charAt(j) == '1')
            rc++;
          else
            break;
        }
        ans = Math.max(ans, lc + rc + 1);
      }
    }

    return ans == 1 ? 0 : ans;
  }

  /**
   * Length of max consecutive 1s by removing 'k' zeros
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int maxCons1sRemoveK0(int A[], int n, int k) {
    int cnt0 = 0;
    int l = 0;
    int maxLen = 0;

    for (int i = 0; i < n; i++) {
      // Keep track of 0 count
      if (A[i] == 0)
        cnt0++;
      // More than k zeros
      while (cnt0 > k) {
        if (A[l] == 0)
          cnt0--;
        l++;
      }
      maxLen = Math.max(maxLen, i - l + 1);
    }
    return maxLen;
  }

  /**
   * Length of max consecutive 1s by swapping atmost one '0'
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int maxCons1sSwap0(String A, int N) {
    int cnt1 = 0, maxCnt = 0, temp = 0;
    // Find max consecutive 1s
    for (int i = 0; i < N; i++) {
      if (A.charAt(i) == '1') {
        cnt1++;
        temp++;
      } else {
        maxCnt = Math.max(maxCnt, temp);
        temp = 0;
      }
    }
    maxCnt = Math.max(maxCnt, temp);

    // To store cumulative 1'A
    int[] left = new int[N];
    int[] right = new int[N];

    if (A.charAt(0) == '1')
      left[0] = 1;
    else
      left[0] = 0;

    if (A.charAt(N - 1) == '1')
      right[N - 1] = 1;
    else
      right[N - 1] = 0;

    // Counting cumulative 1'A from left
    for (int i = 1; i < N; i++) {
      if (A.charAt(i) == '1')
        left[i] = left[i - 1] + 1;
      // If 0 then start new cumulative
      // one from that i
      else
        left[i] = 0;
    }

    for (int i = N - 2; i >= 0; i--) {
      if (A.charAt(i) == '1')
        right[i] = right[i + 1] + 1;
      else
        right[i] = 0;
    }

    for (int i = 1; i < N - 1; i++) {
      // perform step 3 of the approach
      if (A.charAt(i) == '0') {
        // step 3
        int sum = left[i - 1] + right[i + 1];

        if (sum < cnt1)
          maxCnt = Math.max(maxCnt, sum + 1);

        else
          maxCnt = Math.max(maxCnt, sum);
      }
    }

    return maxCnt;

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

    // int A[] = { 2, 4, 5, 4, 10 }; // 90
    // int cost[] = { 40, 30, 20, 10, 40 };
    // System.out.println(minCoschristmasTrees(A, cost, A.length));

    // int A[] = { 1, 0, 0, 1, 0, 1, 0, 1 }; // 5
    // System.out.println(maxCons1sRemoveK0(A, A.length, 2));

    // String str = "1111011111"; // 9
    // String str = "000000000000000"; // 0
    String str = "111011101"; // 7
    System.out.println(maxCons1sSwap0(str, str.length()));
  }
}

package lectures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Day26Hashing {

  /**
   * Find the first non-repeating element
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static int firstNonRepeating(int[] A, int N) {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (int x : A) {
      if (map.containsKey(x)) {
        map.put(x, map.get(x) + 1);
      } else {
        map.put(x, 1);
      }
    }

    for (int x : map.keySet()) {
      if (map.get(x) == 1) {
        return x;
      }
    }

    return -1;
  }

  /**
   * Check if there exists a subarrays with sum zero
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         - Create a prefix sum array
   *         - Now put every element of prefix[] in a hashset
   *         - If the no. of (distinct) elements in the hashset is less than N, it
   *         contains sum 0 subarray
   *         - Eg: {2, 2, 1, -3, 4, 3, -4} // prefix: 2 4 5 2 6 9 5
   *         - We have, A[0] + A[1] + A[2] == 5
   *         - Also, (A[0] + A[1] + A[2]) + (A[3] + A[4] + A[5] + A[6]) == 5
   *         - Hence, A[3] + A[4] + A[5] + A[6] == 0
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static boolean hasSubArraySumZero(int[] A, int N) {
    HashSet<Integer> set = new HashSet<>();
    int[] prefix = prefix(A, N);

    for (int x : prefix) {
      // IMP: Edge case
      if (x == 0) {
        return true;
      }

      set.add(x);
    }

    return set.size() < N;
  }

  /* Helper function to create prefix array */
  public static int[] prefix(int[] A, int N) {
    int[] prefix = new int[N];
    prefix[0] = A[0];
    for (int i = 1; i < N; i++) {
      prefix[i] = A[i] + prefix[i - 1];
    }

    return prefix;
  }

  /**
   * Find the longest subarray with sum zero
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static int longestSubArraySumZero(int[] A, int N) {
    int ans = 0;
    for (int i = 0; i < N; i++) {
      int curr = 0;
      for (int j = i; j < N; j++) {
        curr += A[j];
        if (curr == 0) {
          ans = Math.max(ans, j - i + 1);
        }
      }
    }

    return ans;
  }

  /**
   * Find the longest subarray with sum zero - Optimized
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static int longestSubArraySumZeroOptimized(int[] A, int N) {
    int[] prefix = new int[N];
    int ans = 0;

    prefix[0] = A[0];

    // Edge case: 1
    if (prefix[0] == 0) {
      ans = 1;
    }

    // Create a prefix arary
    for (int i = 1; i < N; i++) {
      prefix[i] = A[i] + prefix[i - 1];
      // Edge case: 2
      if (prefix[i] == 0) {
        ans = i + 1;
      }
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    int st = 0, end = 0;
    for (int i = 0; i < N; i++) {
      if (map.containsKey(prefix[i])) {
        st = map.get(prefix[i]) + 1;
        end = i;
        ans = Math.max(ans, end - st + 1);
      } else {
        map.put(prefix[i], i);
      }
    }

    return ans;
  }

  /**
   * Longest subarray with sum 0 - w/o prefix sum
   * 
   * @param A
   * @param N
   * @return
   */
  public static int longestSubArraySumZeroWoPrefix(int[] A, int N) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int maxLen = 0;

    int i = -1, sum = 0;
    map.put(sum, i);

    while (i < N - 1) {
      i++;
      sum += A[i];

      if (map.containsKey(sum)) {
        int len = i - map.get(sum);
        maxLen = Math.max(len, maxLen);
      } else {
        map.put(sum, i);
      }
    }

    return maxLen;
  }

  public static void main(String[] args) {
    // int[] A = { 1, 2, 3, 2, 5, 1, 2 }; // 3
    // int[] A = { 1, 2, 1, 2, 3, 4, 3 }; // 4
    // int[] A = { 8, 8, 8, 8, 8, 8 };
    // System.out.println(firstNonRepeating(A, A.length));

    // prefix: [2 4 5 2 6 9 5]
    // int[] A = { 2, 2, 1, -3, 4, 3, -4 }; // true
    // prefix: [3 2 4 0 -2]
    // int[] A = { 3, -1, 2, -4, -2 }; // true
    // System.out.println(hasSubArraySumZero(A, A.length));

    // prefix: [3 6 10 5 3 5 6 3 6 5 1 0]
    // int[] A = { 3, 3, 4, -5, -2, 2, 1, -3, 3, -1, -4, -1 }; // 12
    // int[] A = { 15, -2, 2, -8, 1, 7, 10, 23 }; // 5
    // int[] A = { 1, 0, 3 }; // 1
    // int[] A = { 0, 8, 10, -3 };
    // System.out.println(longestSubArraySumZero(A, A.length));
    // System.out.println(longestSubArraySumZeroOptimized(A, A.length));
    // System.out.println(longestSubArraySumZeroWoPrefix(A, A.length));
  }
}
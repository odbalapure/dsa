package intermediate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day27Hashing {

  /**
   * No. of subarrays with sum 0
   * 
   * @param A
   * @param N
   * @return
   */
  public static int numSubArraySumZero(int[] A, int N) {
    int[] prefix = Day26Hashing.prefix(A, N);
    HashMap<Integer, Integer> map = new HashMap<>();
    int ans = 0;

    for (int i = 0; i < N; i++) {
      ans += prefix[i] == 0 ? 1 : 0;
    }

    for (int i = 0; i < N; i++) {
      if (map.containsKey(prefix[i])) {
        map.put(prefix[i], map.get(prefix[i]) + 1);
      } else {
        map.put(prefix[i], 1);
      }
    }

    for (int key : map.keySet()) {
      int n = map.get(key);
      ans += (n * (n - 1)) / 2;
    }

    return ans;
  }

  /**
   * Find max length of a subarray which has equal no. of 0s and 1s
   * 
   * @param A
   * @param N
   * @return
   */
  public static int equal10(int[] A, int N) {
    // Replace all 0s with -1s
    for (int i = 0; i < N; i++) {
      if (A[i] == 0) {
        A[i] = -1;
      }
    }

    // Function that finds the longest subarray with sum 0
    int len = Day26Hashing.longestSubArraySumZero(A, N);
    return len;
  }

  /**
   * Find if any pair A[i] + A[j] == K and i != j
   * 
   * @param A
   * @param N
   * @return
   * 
   *         We know that A[i] + A[j] == K
   *         That means A[j] = K - A[i]
   */
  public static boolean pairSum(int[] A, int N, int K) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < N; i++) {
      if (set.contains(K - A[i])) {
        return true;
      }

      set.add(A[i]);
    }

    return false;
  }

  /**
   * Find distinct no. of elements in an array
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N * K)
   *         Space: O(K)
   */
  public static ArrayList<Integer> distinctElementInWindow(int[] A, int N, int K) {
    HashSet<Integer> set = new HashSet<>();
    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 0; i < N - K + 1; i++) {
      for (int j = 0; j < K; j++) {
        set.add(A[i + j]);
      }

      list.add(set.size());
      set.clear();
    }

    return list;
  }

  /**
   * Find distinct no. of elements in an array
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(K)
   */
  public static ArrayList<Integer> distinctElementInWindowBetter(int[] A, int N, int K) {
    HashMap<Integer, Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();

    // Place first "k" elements in the map
    for (int i = 0; i < K; i++) {
      if (map.containsKey(A[i])) {
        map.put(A[i], map.get(A[i]) + 1);
      } else {
        map.put(A[i], 1);
      }
    }

    list.add(map.size());

    // Process remaining N-k elements
    for (int i = K; i < N; i++) {
      int st = A[i - K], end = A[i];

      // Adding element
      if (map.containsKey(end)) {
        map.put(end, map.get(end) + 1);
      } else {
        map.put(end, 1);
      }

      // Removing element
      int freq = map.get(st);
      if (freq == 1) {
        map.remove(st);
      } else {
        map.put(st, map.get(st) - 1);
      }

      list.add(map.size());
    }

    return list;
  }

  /**
   * Find the count of zero sum subarrays
   * 
   * @param A
   * @param N
   * @return
   */
  public static int numOfSubArraySumZeroWoPrefix(int[] A, int N) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int count = 0;
    int i = -1, sum = 0;

    while (i < N - 1) {
      i++;
      sum += A[i];

      if (map.containsKey(sum)) {
        count += map.get(sum);
        map.put(sum, map.get(sum) + 1);
      } else {
        map.put(sum, 1);
      }
    }

    return count;
  }

  public static void main(String[] args) {
    // Selecting 2/3 items will be N * (N - 1) => 2 * (2 - 1) => 2
    // [ (prefix[0], prefix[3]), (prefix[0], prefix[6]), (prefix[3], prefix[6]) ]
    // prefix: [3 5 8 3 5 8 3]
    // int[] A = { 3, 2, 3, -5, 2, 3, -5 }; // 5
    // prefix: [-2 0 2 -1 0 3 0]
    // int[] A = { -2, 2, 2, -3, 1, 3, -3 }; // 6
    // System.out.println(numSubArraySumZero(A, A.length));
    // System.out.println(numOfSubArraySumZeroWoPrefix(A, A.length));

    // int[] A = { 0, 0, 1, 0, 1, 0, 1, 1 }; // 8
    // int[] A = { 1, 1, 1, 1 }; // 0
    // int[] A = { 0, 0, 1, 1, 0 }; // 4
    // System.out.println(equal10(A, A.length));

    // int[] A = { 0, -1, 2, -3, 1 }; // true
    // System.out.println(pairSum(A, A.length, -2));
    // int[] A = { 1, -2, 1, 0, 5 }; // false
    // System.out.println(pairSum(A, A.length, 0));
    // int[] A = { 8, 9, 1, -2, 4, 11 }; // false
    // System.out.println(pairSum(A, A.length, 8));

    // int[] A = { 2, 4, 3, 8, 3, 9, 4, 9, 4 }; // 4 3 3 4 3 2
    // System.out.println(distinctElementInWindow(A, A.length, 4));
    // System.out.println(distinctElementInWindowBetter(A, A.length, 4));
    // int[] A = { 1, 2, 1, 3, 4, 3 }; // 2 3 3 2
    // System.out.println(distinctElementInWindowBetter(A, A.length, 3));
  }
}

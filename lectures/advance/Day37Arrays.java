package advance;

public class Day37Arrays {

  /**
   * Find the max height a person can go from left to right standing on a building
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         - Start from right most index
   *         - Have 2 arrays
   *         \_ maxR[]: to store the max height on right
   *         \_ ans[]: to store the difference of (maxR[i] - A[i])
   *         - Return the ans[]
   *         `
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int[] maxHeightBuilding(int[] A, int N) {
    int[] maxR = new int[N];
    int[] ans = new int[N];

    maxR[N - 1] = A[N - 1];

    for (int i = N - 2; i >= 0; i--) {
      maxR[i] = Math.max(maxR[i + 1], A[i]);
      ans[i] = maxR[i] - A[i];
    }

    // A[] -> [6, 4, 3, 5, 1, 4]
    // maxR[] -> [6, 5, 5, 5, 4, 4]
    // ans[] -> [0, 1, 2, 0, 3, 0]

    return ans;
  }

  /**
   * Trapping rain water
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         - Create 2 arrays
   *         \_ rMax[]: stores max height on right side
   *         \_ lMax[]: stores max height on left side
   *         - Once we have those arrays, for each index do
   *         \_ ans += min(rMax[i], lMax[i]) - A[i]
   *         - Return ans
   * 
   *         COMEPLXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int trappingRainWater(int[] A, int N) {
    int ans = 0;
    int[] rMax = new int[N], lMax = new int[N];

    // Find right max for each index
    rMax[N - 1] = A[N - 1];
    for (int i = N - 2; i >= 0; i--) {
      rMax[i] = Math.max(rMax[i + 1], A[i]);
    }

    // Find left max for each index
    lMax[0] = A[0];
    for (int i = 1; i < N; i++) {
      lMax[i] = Math.max(lMax[i - 1], A[i]);
    }

    // A[] -> [6, 4, 3, 5, 1, 4]
    // rMax[] -> [6, 6, 5, 5, 4, 4]
    // lMax[] -> [6, 6, 6, 6, 6, 6]

    for (int i = 0; i < N; i++) {
      ans += Math.min(lMax[i], rMax[i]) - A[i];
    }

    return ans;
  }

  /**
   * Given a sorted list of intervals - merge overalpping intervals
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static void mergeOverlappingIntervals(int[] S, int[] E, int N) {
    int L = S[0], R = E[0];
    for (int i = 1; i < N; i++) {
      if (S[i] <= R) {
        R = Math.max(R, E[i]);
      } else {
        System.out.println("[" + L + ", " + R + "]");
        L = S[i];
        R = E[i];
      }
    }
  }

  /**
   * Merge non overlapping intervals (sorted)
   * 
   * @param S
   * @param N
   */
  public static void insertInterval(int[] S, int[] E, int N, int L, int R) {
    int start = 0, end = 0;
    for (int i=0; i<N; i++) {
      start = Math.min(L, S[i]);
      end = Math.max(R, E[i]);
    }

    System.out.println(start + ", " + end);
  }

  /**
   * Find the first missing no. (no duplicates present)
   * 
   * @param A
   * @param N
   * @return
   */
  public static int findFirstMissingNo(int[] A, int N) {
    int missing = 0;
    for (int i = 0; i < N; i++) {
      boolean isPresent = false;
      for (int j = i; j < N; j++) {
        if (A[j] == i) {
          isPresent = true;
          break;
        }
      }

      if (!isPresent) {
        missing = i;
      } else {
        break;
      }
    }

    return missing;
  }

  public static void main(String[] args) {
    // int[] A = { 6, 4, 3, 5, 1, 4 }; // [0 1 2 0 3 0]
    // System.out.println(Arrays.toString(maxHeightBuilding(A, A.length)));

    // int[] A = { 6, 4, 3, 5, 1, 4 }; // 6
    // int[] A = { 3, 2, 4, 1, 3, 5, 3, 4 }; // 6
    // int[] A = { 3, 0, 2, 0, 4 }; // 7
    // System.out.println(trappingRainWater(A, A.length));

    // int[] S = { 1, 3, 5, 10, 12, 13, 16, 17 };
    // int[] E = { 3, 8, 6, 12, 15, 14, 17, 18 }; // [1, 8] [10, 15]
    // mergeOverlappingIntervals(S, E, S.length);

    int[] S = {1,5,8};
    int[] E = {3,7,12};
    insertInterval(S, E, S.length, 4, 6);

    // int[] A = { 10, 3, 1, 2, 5, -8, -3, 4 }; // 6
    // int[] A = { 10, 15, 3, 2, 8 }; // 1
    // System.out.println(findFirstMissingNo(A, A.length));
  }
}

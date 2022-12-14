package advance;

public class Day35Arrays {

  /**
   * Max absolute difference
   * 
   * @param A
   * @param N
   * @return
   */
  public static int maxAbsoluteDifference(int[] A, int N) {
    int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
    int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
    int ans = 0;

    for (int i = 0; i < N; i++) {
      max1 = Math.max(max1, A[i] + i);
      max2 = Math.max(max2, A[i] - i);
      min1 = Math.min(min1, A[i] + i);
      min2 = Math.min(min2, A[i] - i);
    }

    ans = Math.max(ans, max2 - min2);
    ans = Math.max(ans, max1 - min1);
    return ans;
  }

  /**
   * Find the max sub array sum
   * 
   * @param A
   * @param N
   * @return
   */
  public static int maxSubarraySum(int[] A, int N) {
    int ans = A[0], sum = 0;
    for (int i = 0; i < N; i++) {
      sum += A[i];
      ans = Math.max(ans, sum);
      if (sum < 0) {
        sum = 0;
      }
    }

    return ans;
  }

  /**
   * Perform queries Q(i, x) on array, where "i" is an index and "x" is a value
   * 
   * @param A
   * @param Q
   * @return
   */
  public static int[] queryIx(int[] A, int[][] Q) {
    for (int[] query : Q) {
      int i = query[0];
      int x = query[1];
      A[i] += x;
    }

    for (int i = 1; i < A.length; i++) {
      A[i] += A[i - 1];
    }

    return A;
  }

  /**
   * Perform queries Q(i, x) on array, where "i" is an index and "x" is a value
   * 
   * @param A
   * @param Q
   * @return
   */
  public static int[] queryIJx(int[] A, int[][] Q) {
    for (int[] query : Q) {
      int i = query[0] - 1;
      int j = query[1] - 1;
      int x = query[2];
      A[i] += x;

      if ((j + 1) < A.length) {
        A[j + 1] -= x;
      }
    }

    for (int i = 1; i < A.length; i++) {
      A[i] += A[i - 1];
    }

    return A;
  }

  public static void main(String[] args) {
    // int[] A = { 1, 3, -2 };
    // int[] A = { 2, 2, 2 }; // 2
    int[] A = { 55, -8, 43, 52, 8, 59, -91, -79, -18, -94 };
    System.out.println(maxAbsoluteDifference(A, A.length));

    // int[] A = { 10, -5, 7, 8, 1, 2 }; // 23
    // int[] A = { -2, -3, 4, -1, -2, 1, 5, -3 }; // 7
    // int[] A = { -3, -8, -1, -5 };
    // System.out.println(maxSubarraySum(A, A.length));

    // int[] A = { 0, 0, 0, 0, 0, 0, 0 }; // [0, 3, 3, 4, 6, 6, 6]
    // System.out.println(Arrays.toString(queryIx(A, new int[][] { { 1, 3 }, { 4, 2
    // }, { 3, 1 } })));

    // int[] A = { 0, 0, 0, 0, 0, 0, 0 }; // [0, 2, 5, 5, 3, 2, -1]
    // System.out.println(Arrays.toString(queryIJx(A, new int[][] { { 1, 3, 2 }, {
    // 2, 5, 3 }, { 5, 6, -1 } })));

    // int[] A = { 0, 0, 0, 0, 0 }; // [10, 55, 45, 25, 25]
    // System.out.println(Arrays.toString(queryIJx(A, new int[][] { { 1, 2, 10 }, {
    // 2, 3, 20 }, { 2, 5, 25 } })));
  }
}

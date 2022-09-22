import java.util.Arrays;

public class Day11ArraysPrefix {

  /**
   * Return sum queries
   * 
   * @param A
   * @param B
   * @return
   */
  public static long[] prefixSumQuery(int[] A, int[][] B) {
    long[] prefix = createPrefix(A, A.length);
    long[] res = new long[B.length];
    long sum = 0;
    int indx = 0;

    for (int[] query : B) {
      int L = query[0], R = query[1];
      if (L == 1) {
        sum = prefix[R - 1];
      } else {
        sum = prefix[R - 1] - prefix[L - 2];
      }

      // 1 3 6 10 15
      res[indx] = sum;
    }

    return res;
  }

  /* Create a prefix array */
  public static long[] createPrefix(int[] A, int N) {
    long[] prefix = new long[N];
    prefix[0] = A[0];

    for (int i = 1; i < N; i++) {
      prefix[i] = prefix[i - 1] + A[i];
    }

    return prefix;
  }

  public static void main(String[] args) {
    int[] A = { 1, 2, 3, 4, 5 };
    int[][] B = { { 1, 4 }, { 2, 3 } };
    System.out.println(Arrays.toString(prefixSumQuery(A, B)));
  }
}

package advance;

import java.util.ArrayList;

public class Day36Arrays2D {

  /**
   * Find the sum of a given submatrix
   * NOTE: We need 2 points (diagonals) to find a submatrix
   * 
   * @param A
   * @return
   */
  public static int sumSubmatrix(int[][] A, int[][] Q) {
    int ans = 0;

    int sx = Q[0][0], sy = Q[0][1];
    int ex = Q[1][0], ey = Q[1][1];

    for (int i = sx; i <= ex; i++) {
      for (int j = sy; j <= ey; j++) {
        ans += A[i][j];
      }
    }

    return ans;
  }

  /**
   * Find sum of submatrix for no. of queries
   * 
   * @param A
   * @param Q
   * @return
   * 
   *         APPROACH
   *         - Create a prefix sum matrix
   *         \_ Process the 1st (0th) row and column
   *         \_ Use pre[i][j] = A[i][j] + pre[i-1][j] + pre[i][j-1] -
   *         pre[i-1][j-1]
   *         - Calculate prefix sum for A[i][j]
   *         \_ pre[ex][ey] - pre[sx - 1][ey] - pre[ex][sy - 1]
   */
  public static ArrayList<Integer> sumSubmatrixQueries(int[][] A, int[][] Q) {
    int R = A.length;
    int C = A[0].length;

    ArrayList<Integer> ans = new ArrayList<>();
    int[][] prefix = createPrefixMatrix(A, R, C);

    for (int[] queries : Q) {
      int sx = queries[0], sy = queries[1];
      int ex = queries[2], ey = queries[3];
      ans.add(prefix[ex][ey] - prefix[sx - 1][ey] - prefix[ex][sy - 1]);
    }

    return ans;
  }

  /**
   * Create a prefix sum matrix
   * 
   * @param A
   * @param R
   * @param C
   * @return
   */
  public static int[][] createPrefixMatrix(int[][] A, int R, int C) {
    int[][] prefix = new int[R][C];
    prefix[0][0] = A[0][0];

    // Process 1st row
    for (int i = 1; i < C; i++) {
      prefix[0][i] = prefix[0][i - 1] + A[0][i];
    }

    // Process 1st column
    for (int i = 1; i < R; i++) {
      prefix[i][0] = prefix[i - 1][0] + A[i][0];
    }

    // Apply prefix sum formula
    for (int i = 1; i < R; i++) {
      for (int j = 1; j < C; j++) {
        prefix[i][j] = A[i][j] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
      }
    }

    return prefix;
  }

  /**
   * Find the sum of all sub matrix
   * 
   * @param A
   * @return
   */
  public static int sumAllSubMatrix(int[][] A) {
    int ans = 0;
    int N = A.length, M = A[0].length;
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        ans += A[i][j] * ((i + 1) * (j + 1)) * ((N - i) * (M - j));
      }
    }

    return ans;
  }

  /**
   * Find the max sum of a sub matrix
   * @param A
   * @return
   */
  public static int maxSubMatrixSum(int[][] A) {
    return 0;
  }

  /**
   * Find element in a row and column sorted matrix
   * 
   * @param A
   * @return
   */
  public static boolean findElement(int[][] A, int x) {
    int R = A.length, C = A[0].length;
    int i = 0, j = C - 1;

    while (i < R && j >= 0) {
      if (A[i][j] == x) {
        return true;
      } else if (A[i][j] > x) {
        // Element is smaller go left
        j--;
      } else {
        // Element is large go down
        i++;
      }
    }
    
    return false;
  }

  public static void main(String[] args) {
    // int[][] A = { { 1, 3, 5, 2, -1 }, { 4, 8, 5, 0, 6 }, { 10, 20, -1, 3, 5 }, {
    // 1, 5, -5, 10, 6 } };
    // System.out.println(sumSubmatrix(A, new int[][] { { 1, 0 }, { 3, 2 } })); //
    // 47
    // System.out.println(sumSubmatrix(A, new int[][] { { 1, 1 }, { 2, 4 } })); //
    // 46

    // int[][] A = { { 1, 3, 5, 2, -1 }, { 4, 8, 5, 0, 6 }, { 10, 20, -1, 3, 5 }, {
    // 1, 5, -5, 10, 6 } };
    // System.out.println(sumSubmatrixQueries(A, new int[][] { { 1, 1, 3, 3 } }));
    // // [44]

    // int[][] A = { { 1, 3, 5, 2, -1 }, { 4, 8, 5, 0, 6 }, { 10, 20, -1, 3, 5 }, {
    // 1, 5, -5, 10, 6 } }; // 3234
    // int[][] A = { { 1, 1, 1 },
    //     { 1, 1, 1 },
    //     { 1, 1, 1 } }; // 100
    // System.out.println(sumAllSubMatrix(A));

    int[][] A = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    System.out.println(findElement(A, 6)); // true
  }
}

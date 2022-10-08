package intermediate;

import java.util.Arrays;

public class Day9Matrix {

  /**
   * Given a matrix print the left and right diagonal
   * 
   * @param A
   * 
   *          COMEPLXITY
   *          Time: O(2N) =~ O(N)
   *          Space: O(N)
   */
  public static void printDiagonals(int[][] A, int N) {
    int i = 0, j = 0;

    // Left to right
    while (i < N) {
      System.out.print(A[i][i] + " ");
      i++;
    }

    System.out.println();

    // Right to left
    i = 0;
    j = N - 1;
    while (i < N && j >= 0) {
      System.out.print(A[i][j] + " ");
      i++;
      j--;
    }
  }

  /**
   * Find transpose of a matrix
   * 
   * @param A
   * @param N
   * 
   *          COMPLEXITY
   *          Time: O(N*N/2) =~ O(N*N)
   *          Space: O(1) [Only possible for N X N matrix]
   */
  public static void transposeMatrix(int[][] A, int N) {
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        if (i == j) {
          continue;
        }

        int temp = A[i][j];
        A[i][j] = A[j][i];
        A[j][i] = temp;
      }
    }

    // printMatrix(A);
  }

  /**
   * Rotate a matrix by 90 degree clock wise
   * 
   * Clock wise rotation is found by
   * 1. Transpose and followed by
   * 2. Reversing each row
   * 
   * @param A
   * @param N
   * 
   *          COMPLEXITY
   *          Time: O(N*N) + O(N*N) =~ O(N*N)
   *          Space: O(1)
   */
  public static void rotateMatrix90DegreeRight(int[][] A, int N) {
    // Find tranpose
    transposeMatrix(A, N);

    // Reverse each row
    for (int i = 0; i < N; i++) {
      int s = 0, e = N - 1;
      while (s < e) {
        int temp = A[i][s];
        A[i][s] = A[i][e];
        A[i][e] = temp;
        s++;
        e--;
      }
    }

    // printMatrix(A);
  }

  /* Helper function to print the matrix */
  public static void printMatrix(int[][] A) {
    for (int[] x : A) {
      System.out.print(Arrays.toString(x) + " ");
    }
  }

  public static void main(String[] args) {
    // int[][] A = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // [1 5 9] [3 5 7]
    // printDiagonals(A, A.length);

    // int[][] A = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // [1, 4, 7] [2, 5, 8]
    // [3, 6, 9]
    // transposeMatrix(A, A.length);

    int[][] A = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
        { 21, 22, 23, 24, 25 } }; // [21, 16, 11, 6, 1] [22, 17, 12, 7, 2] [23, 18, 13, 8, 3] [24, 19, 14, 9, 4]
                                  // [25, 20, 15, 10, 5]
    rotateMatrix90DegreeRight(A, A.length);
  }
}

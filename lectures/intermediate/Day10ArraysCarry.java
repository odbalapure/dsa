package intermediate;

import java.util.ArrayList;

public class Day10ArraysCarry {

  /**
   * Count no. of valid pairs of 'a' and 'g'
   * 
   * @param A
   * @param N
   * @return
   */
  public static int countValidPairs(char[] A, int N) {
    int ans = 0, cg = 0;

    /* Right to left */
    // for (int i = N - 1; i >= 0; i--) {
    // if (A[i] == 'g') {
    // cg++;
    // } else if (A[i] == 'a') {
    // ans += cg;
    // }
    // }

    /* Left to right */
    for (int i = 0; i < N; i++) {
      if (A[i] == 'a') {
        cg++;
      } else if (A[i] == 'g') {
        ans += cg;
      }
    }

    return ans;
  }

  /**
   * Find leaders in an array
   * 
   * @param A
   * @param N
   * @return
   */
  public static ArrayList<Integer> leaders(int[] A, int N) {
    ArrayList<Integer> list = new ArrayList<>();

    /* Brute force */
    // for (int i=0; i<N; i++) {
    // boolean isLeader = false;
    // for (int j=i+1; j<N; j++) {
    // if (A[j] < A[i]) {
    // isLeader = true;
    // } else {
    // break;
    // }
    // }

    // if (isLeader) {
    // list.add(A[i]);
    // }
    // }

    int maxFromRight = A[N - 1];
    list.add(maxFromRight);

    /* Optimized */
    for (int i = N - 2; i >= 0; i--) {
      if (A[i] > maxFromRight) {
        maxFromRight = A[i];
        list.add(A[i]);
      }
    }

    return list;
  }

  /**
   * Find the length of the smallest subarray with 'min' and 'max' elements
   * - min and max will always be at boundaries
   * - Subarray either ends/starts with min/max and vice versa
   *
   * @param A
   * @param N
   * @return
   */
  public static int minMaxInSmallestSubLength(int[] A, int N) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    int ans = Integer.MAX_VALUE;

    // Step 1: Find min and max elements
    for (int j = 0; j < N; j++) {
      max = Math.max(A[j], max);
      min = Math.min(A[j], min);
    }

    // In case if all the elements are same in the array
    if (min == max) {
      return 1;
    }

    // Step 2: Find the nearest min and max
    for (int i = 0; i < N; i++) {
      if (A[i] == min) { // Finding nearest max
        for (int j = i + 1; j < N; j++) {
          if (A[j] == min) {
            i = j;
            break;
          }

          if (A[j] == max) {
            ans = Math.min(ans, j - i + 1);
            break;
          }
        }
      } else if (A[i] == max) { // Finding nearest min
        for (int j = i + 1; j < N; j++) {
          if (A[j] == max) {
            i = j;
            break;
          }

          if (A[i] == min) {
            ans = Math.min(ans, j - i + 1);
            break;
          }
        }
      }
    }

    return ans;
  }

  /**
   * Find the length of the smallest subarray with 'min' and 'max' elements
   *
   * @param A
   * @param N
   * @return
   */
  public static int minMaxInSmallestSubLengthBetter(int[] A, int N) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    int ans = Integer.MAX_VALUE;
    int minI = -1, maxI = -1;

    // Step 1: Find min and max elements
    for (int j = 0; j < N; j++) {
      max = Math.max(A[j], max);
      min = Math.min(A[j], min);
    }

    // In case if all the elements are same in the array
    if (min == max) {
      return 1;
    }

    for (int i = N - 1; i >= 0; i--) {
      if (A[i] == min) {
        minI = i;
        if (minI != -1 && maxI != -1) {
          ans = Math.min(ans, Math.abs(maxI - minI) + 1);
        }
      } else if (A[i] == max) {
        maxI = i;
        if (minI != -1 && maxI != -1) {
          ans = Math.min(ans, Math.abs(maxI - minI) + 1);
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    // char[] A = { 'a', 'd', 'g', 'f', 'g', 'g', 'e', 'g' };
    // System.out.println(countValidPairs(A, A.length));

    // int[] A = { 15, -1, 7, 2, 5, 4, 2, 3 };
    // int[] A = { 93, 57, 83, 41, 100, 10, 79, 27, 94, 22, 4, 96, 48, 18, 89, 37,
    // 21, 5, 46, 81, 15, 30, 47, 23, 34, 65,
    // 55, 9, 36, 20, 54, 17, 7, 56, 78, 84, 87, 97, 16, 69, 28, 11, 44, 49, 8, 25,
    // 98, 75, 53, 62, 19, 24, 80, 68, 50,
    // 91, 1, 86, 77, 14, 72, 66, 42, 63, 73, 45, 31, 61, 85, 64, 35, 32, 92, 71,
    // 74, 3, 99, 52, 90, 43, 6, 40, 38, 2,
    // 12, 59, 29, 82, 76, 60, 67, 13, 70, 58, 39, 33, 95, 88, 51, 26 };
    // System.out.println(leaders(A, A.length)); // [4, 5, 7, 15]
    // System.out.println(leaders(A, A.length)); // [26, 51, 88, 95, 99, 100]

    // int[] A = { 1, 2, 3, 1, 3, 4, 6, 4, 6, 3 }; // 4
    // int[] A = { 2, 2, 6, 4, 5, 1, 5, 2, 6, 4, 1 }; // 3
    // int[] A = { 1, 6, 4, 6, 5, 1, 5, 2, 6, 4, 4, 2, 1 }; // 2
    // System.out.println(minMaxInSmallestSubLength(A, A.length));
    // System.out.println(minMaxInSmallestSubLengthBetter(A, A.length));
  }
}

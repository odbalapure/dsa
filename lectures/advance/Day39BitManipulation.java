package advance;

import java.util.Arrays;

public class Day39BitManipulation {

  /**
   * Find two unique elements in an array where every other element appears twice
   * 
   * @param A
   * @param N
   * @return
   */
  public static int[] uniqueElement(int[] A, int N) {
    int xor = 0;

    // Find the XOR result
    for (int i = 0; i < N; i++) {
      xor ^= A[i];
    }

    // Find the position of the bit which is set in 'xor'
    int b = -1;
    for (int i = 0; i < 32; i++) {
      if (((xor >> i) & 1) == 1) {
        b = i;
        break;
      }
    }

    // A[] -> [2 4 2 5 4 6 7 6]
    // xor -> 2 (0010)
    // ith set bit is 1 -> [2 2 6 7 6]
    // ith set bit is 0 -> [4 5 4]

    // Check if the bit at position 'b' is set in A[i] or not
    // Divide the array in two parts depending upon this condition
    int x = 0, y = 0;
    for (int i = 0; i < N; i++) {
      if (((A[i] >> b) & 1) == 1) {
        x ^= A[i];
      } else {
        y ^= A[i];
      }
    }

    return new int[] { x, y };
  }

  /**
   * Find 2 missing no. in an array with elements from [1 to N+2]
   * 
   * @param A
   * @param N
   * @return
   */
  public static int[] find2Missing(int[] A, int N) {
    int xor = 0;

    for (int i = 0; i < N; i++) {
      xor ^= A[i];
    }

    for (int i = 1; i <= N + 2; i++) {
      xor ^= i;
    }

    int b = -1;
    for (int i = 0; i < 32; i++) {
      if (((xor >> i) & 1) == 1) {
        b = i;
        break;
      }
    }

    int x = 0, y = 0;
    for (int i = 0; i < N; i++) {
      if (((A[i] >> b) & 1) == 1) {
        x ^= A[i];
      } else {
        y ^= A[i];
      }
    }

    for (int i = 1; i <= N + 2; i++) {
      if (((i >> b) & 1) == 1) {
        x ^= i;
      } else {
        y ^= i;
      }
    }

    return new int[] { x, y };
  }

  /**
   * Find the max value of A[i] & A[j] where i != j
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: (N)
   *         Space: O(1)
   */
  public static int maxAnd(int[] A, int N) {
    int ans = 0;
    // Travel from MSB to LSB
    for (int b = 31; b >= 0; b--) {
      int count = 0;
      // Counting the no. of elements where current bit is 1
      for (int i = 0; i < N; i++) {
        count += (A[i] >> b) & 1;
      }

      if (count >= 2) {
        ans |= (1 << b);
        for (int i = 0; i < N; i++) {
          // Set A[i] to 0 if the 'b' bit is set to 0
          // The reason we are doing this is coz we want more 1s on left side
          if (((A[i] >> b) & 1) == 0) {
            A[i] = 0;
          }
        }
      }
    }

    return ans;
  }

  /**
   * Find XOR sum of all pair (A[i], A[j]) where i != j
   * NOTE: [a ^ (x + y) != a^x + a^y]
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int maxXor(int[] A, int N) {
    int ans = 0, count = 0;
    for (int b = 0; b < 32; b++) {
      int set = 0, unset = 0;
      for (int i = 0; i < N; i++) {
        if (((A[i] >> b) & 1) == 1) {
          set += 1;
        } else {
          unset += 1;
        }
      }
      count = set * unset;
      ans += count * (1 << b);
    }

    return ans;
  }

  public static void main(String[] args) {
    // int[] A = { 2, 4, 2, 5, 4, 6, 7, 6 }; // [5 7]
    // System.out.println(Arrays.toString(uniqueElement(A, A.length)));

    // int[] A = { 3, 6, 1, 4 }; // [2 5]
    // int[] A = { 3, 1 }; // [2 4]
    // System.out.println(Arrays.toString(find2Missing(A, A.length)));

    // int[] A = { 3, 5, 7 }; // 5
    // int[] A = { 3, 5, 11 }; // 3
    // int[] A = { 26, 13, 23, 28, 27, 7, 25 }; // 26
    // System.out.println(maxAnd(A, A.length));

    // int[] A = {3,5,6}; // 14
    // int[] A = { 3, 5, 1, 8 }; // 45
    // System.out.println(maxXor(A, A.length));
  }
}

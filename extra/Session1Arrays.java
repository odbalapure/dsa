import java.util.ArrayList;

public class Session1Arrays {

  /**
   * Pick B elements from both sides and find the max sum
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(B) =~ O(N) [B might be almost equal to N]
   *         Space: O(1)
   * 
   *         APPROACH
   *         - Find sum of left most B elements
   *         - Pick elements from left and subtract it from current sum (going
   *         backwards)
   *         - Pick elements from right and add it to the current sum (going
   *         backwards)
   *         - Keep updating the max sum
   *         - Return max sum
   */
  public static int pickFromBothSides(int[] A, int N, int B) {
    int curr = 0, ans = Integer.MIN_VALUE;

    // Step 1: Sum of all B elements from left
    for (int i = 0; i < B; i++) {
      curr += A[i];
    }

    // "curr" might be the biggest sum so store it
    ans = Math.max(ans, curr);

    // Step 2: Pick remove elements from the left side
    // And try picking up elements from the right side
    int back = B - 1;
    int end = N - 1;
    while (back >= 0) {
      curr = curr - A[back--] + A[end--];
      ans = Math.max(ans, curr);
    }

    return ans;
  }

  /**
   * Find the min no. of presses for faulty switches to turn all the bulbs ON
   * 
   * @param A
   * @param N
   * @return
   */
  public static int minNoOfSwitchPresses(int[] A, int N) {
    int count = 0;

    for (int i = 0; i < N; i++) {
      if (A[i] == 0) {
        A[i] = 1;
        for (int j = i + 1; j < N; j++) {
          if (A[j] == 1) {
            A[j] = 0;
          } else {
            A[j] = 1;
          }
        }

        count++;
      }

    }

    return count;
  }

  /**
   * Find the min no. of presses for faulty switches to turn all the bulbs ON
   * 
   * @param A
   * @param N
   * @return
   */
  public static int minNoOfSwitchPressesBetter(int[] A, int N) {
    int state = 0, ans = 0;

    for (int i = 0; i < N; i++) {
      if (A[i] == state) {
        ans++;
        state = 1 - state;
      }
    }

    return ans;
  }

  /**
   * Find middle indices of altenating subarrays of length (2 * B + 1)
   * 
   * @param A
   * @param N
   * @param B
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N*N)
   *         Space: O(1) [The code demands to return an array/list]
   */
  public static ArrayList<Integer> alternatingSubarrays(int[] A, int N, int B) {
    ArrayList<Integer> ans = new ArrayList<>();
    int len = 2 * B + 1;

    for (int i = 0; i < N - len + 1; i++) {
      boolean flag = true;
      int prev = -1;
      for (int j = 0; j < len; j++) {
        if (prev == A[j + 1]) {
          flag = false;
          break;
        }
      }

      if (flag) {
        // NOTE: If "B" is even/odd the length will always be of odd length
        // Eg: If i=0, B=1 then total length is 3, so middle element will be
        // (i + B) = (0 + 1) = 1
        // Also, we have subarray of length 2B + 1
        // We can write it as B + 1 + B
        // If we are at index "i", to reach center we can do "B + i"
        ans.add(i + B);
      }
    }

    return ans;
  }

  /**
   * Find middle indices of altenating subarrays of length (2 * B + 1)
   * 
   * @param A
   * @param N
   * @param B
   * @return
   */
  public static ArrayList<Integer> alternatingSubarraysBetter(int[] A, int N, int B) {
    ArrayList<Integer> ans = new ArrayList<>();
    int len = 2 * B + 1, count = 0;

    for (int i = 0; i < N - 1; i++) {
      if (A[i] == A[i + 1]) {
        count = 1;
      } else {
        count++;
      }

      if (count >= len) {
        ans.add(i - B + 1);
      }
    }

    return ans;
  }

  /**
   * Find the index, which after removing the sum of even and odd indices become
   * equal
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         - If an index "i" is special, that means the elements after "i" are
   *         shifted towards left
   *         - As a result all the odd index become even and even index become odd
   *         - So, New Even Sum = sum of even index so far + sum of odd index on
   *         right side of "i"
   *         - Also, New Odd Sum = sum of odd index so far + sum of even index on
   *         right side of "i"
   */
  public static int specialIndex(int[] A, int N) {
    int totalEven = 0, totalOdd = 0, count = 0;

    for (int i = 0; i < N; i++) {
      totalEven += (i % 2 == 0) ? A[i] : 0;
      totalOdd += (i % 2 != 0) ? A[i] : 0;
    }

    int currEven = 0, currOdd = 0;
    int newEvenSum = 0, newOddSum = 0;

    for (int i = 0; i < N; i++) {
      if (i % 2 == 0) {
        currEven += A[i];
        newEvenSum = (currEven - A[i]) + (totalOdd - currOdd);
        newOddSum = currOdd + (totalEven - currEven);
      } else {
        currOdd += A[i];
        newOddSum = (currOdd - A[i]) + (totalEven - currEven);
        newEvenSum = currEven + (totalOdd - currOdd);
      }

      count += (newEvenSum == newOddSum) ? 1 : 0;
    }

    return count;
  }

  public static void main(String[] args) {
    // int[] A = { 5, -2, 3, 1, 2 }; // 8
    // int[] A = { 2, 1, 14, 6, 4, 3 };
    // System.out.println(pickFromBothSides(A, A.length, 3));

    // int[] A = { 0, 1, 0, 1 }; // 4
    // System.out.println(minNoOfSwitchPresses(A, A.length));
    // int[] A = { 1, 0, 1, 0 }; // 3
    // int[] A = { 0, 1, 0, 1, 0 }; // 5
    // System.out.println(minNoOfSwitchPressesBetter(A, A.length));

    // int[] A = { 1, 0, 1, 0, 1 }; // [1, 2, 3]
    // System.out.println(alternatingSubarrays(A, A.length, 1));
    // System.out.println(alternatingSubarraysBetter(A, A.length, 1));

    // int[] A = { 2, 1, 6, 4 }; // 1
    // int[] A = { 1, 1, 1 }; // 3
    // System.out.println(specialIndex(A, A.length));
  }
}

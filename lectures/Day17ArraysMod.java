public class Day17ArraysMod {
  /**
   * Remainder = Dividend - Divisor * Quotient
   * r = D - d * q
   * Find the closest factor of "d" less than "D"
   * 
   * Q. Why we need to know MOD?
   * NOTE: 0 to 100 % 5 will have remainders ranging from 0, 2, 4
   * So we can reduce the no. of computations using various MOD rules
   *
   * Addition MOD rule: (a % b) % b == a % b
   * 
   * ====================
   * MOD of negative no
   * ====================
   * Correct answer
   * -40 % 7 = -5 2
   * -60 % 9 = -6 3
   * -30 % 4 = -2 2
   * 
   * NOTE: Only python will give correct results for MOD of -ve value directly
   * You MUST add the divisor again to get the correct answer
   * Eg: -5 + 7 = 2, -6 + 9 = 3 and -2 + 4 = 2
   * 
   * =====================
   * MULTIPLICATIVE RULE
   * =====================
   * 1. (a + b) % M = (a % M + b % M) % M
   * 2. (a X b) % M = (a % M X b % M) % M
   * 
   * 
   * ==============
   * DIVISIBILITY
   * ==============
   * 1. By 3: Add all the nos. and check if the sum can be divided by 3
   * 2. By 4: Last 2 nos. should be divisible by 4
   */

  /**
   * @desc Write a power function
   * @param a
   * @param N
   * @param p
   * @return power
   *
   *         Eg: 2^4 % 10 => 16 % 10 = 6
   *         ans = (1 % 10 X 2 % 10) % 10 = 2
   *         ans = (2 % 10 X 2 % 10) % 10 = 4
   *         ans = (4 % 10 X 2 % 10) % 10 = 8
   *
   *         NOTE: 1, 2 and 4 are values of "ans" in every iteration
   */
  public static int powerModP(int a, int N, int p) {
    int ans = 1;
    for (int i = 1; i <= N; i++) {
      // We would do this for a smaller power
      // ans = ans * a;
      // But we know that (a x b) % M = (a % M X b % M) % M
      ans = (ans % p * a % p) % p;
      // max value will be p - 1 for both mod operations
      // even if "ans" is very huge
    }

    // return ans % p;
    // We do MOD in the for loop itself
    return ans;
  }

  /**
   * @desc Mod of a no. represented as an array
   * @param arr
   * @param p
   * @return Mod value
   * 
   *         COMPLEXITY
   *         Time: O(N^2)
   *         Space: O(1)
   * 
   *         APPROACH
   *         Say we have a no. 365 we know that 365 = 3 X 10^2 + 6 X 10^1 + 5 X
   *         10^0
   *         If we apply the "Additive MOD" rule we can write it as
   *         [ (3 X 10^2) % p + (6 X 10^1) % p + (5 X 10^0) % p ] % p
   */
  public static int aModP(int[] arr, int p) {
    int res = 0, N = arr.length;
    for (int i = 0; i < N; i++) {
      res += ((arr[i] % p * powerModP(10, N - 1 - i, p))) % p;
    }

    return res;
  }

  public static void main(String[] args) {
    System.out.println(powerModP(2, 4, 10));
    // int[] arr = { 1, 2, 3, 4, 4 };
    // int[] arr = { 3, 8, 4, 3, 6, 8, 9 };
    int[] arr = { 1, 2, 3, 1, 6, 7, 6, 7, 6, 7, 8, 6, 7, 8 };
    // 5, 3 => 5 % 3 = 2
    // 9, 3 => 9 % 3 = 0
    // 9, 5 => 9 % 5 = 4
    System.out.println(aModP(arr, 10));
  }
}

package advance;

public class Day40ArraysMod {
  /**
   * =======================
   * MODULAR ARITHEMATICE
   * =======================
   * Mod is basically continous subtration
   * Eg: 10%3 = 10-3 = 7-3 = 4-3 = 1 (Ans: 1)
   * 
   * PROPERTIES
   * - For all x, x % 1 = 0
   * - For all x, x % x = 0
   * - If x < y then x % y = x
   * - For all x, 0 <= x % 4 <= 3
   * 
   * =======================
   * MOD OF -VE
   * =======================
   * -5 % 4 = -1 => -1 + 4 => 3
   * Create a no. line the jump 4 steps to right after getting an answer
   * Eg: You get -1 then go 4 steps to the right, then you get 3
   * 
   * =====================
   * MDO RULES
   * =====================
   * 1. (a + b) % M = (a % M + b % M) % M
   * 2. (a X b) % M = (a % M X b % M) % M
   * 3. (a - b) % M = (a % M - b % M + M) % M (If a < b, so -b % M will give
   * incorrect answer in JAVA, C++)
   * 4. (a / b) % M = (a % M * 1/b % M) % M
   * \_ (3 X ?) % 13 = 1, here "?" is 9
   * \_ Inorder to find the inverse mod you need to find when the "?" gives the
   * result 1, once done return the "?"
   */

  /**
   * Find no. of trailing 0s in a factorial
   * 
   * APPROACH
   * - We get a 0 at the end by 2 X 5
   * - Find the multiples of 5 and its powers
   * \_ Multiple of 5 from 1 to 100 = 100/5 = 20
   * \_ Multiple of 25 from 1 to 100 = 100/25 = 4
   * \_ So total here would be 20 + 4 = 24
   * - Result to return will be
   * \_ Multiples of 5 + 5^2 ... 5^k
   * \_ Go till 5^k < N
   * 
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: log base 5 (N) =~ log(N)
   *         Space: O(1)
   */
  public static int trailingZerosInFactorial(int N) {
    int ans = 0;
    for (int i = 5; i <= N; i *= 5) {
      ans += N / i;
    }
    return ans;
  }

  /**
   * Return (a ^ b) % p
   * 
   * @param a
   * @param b
   * @param p
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(b)
   *         Space: O(1)
   */
  public static int aPowerModP(int a, int b, int p) {
    int ans = 1;
    for (int i = 1; i <= b; i++) {
      // (a % M X b % M) % M
      ans = (ans % p * a % p) % p;
    }
    return ans;
  }

  /**
   * Return (a ^ b) % p - optimized
   * 
   * @param a
   * @param b
   * @param p
   * @return
   * 
   *         APPROACH
   *         - We can cut down steps by using the following technique
   *         - pow(a, b) % m
   *         \_ b is even - (a^2 % m)^b/2 % m
   *         \_ b is odd - a X (a^2 % m)^b/2 % m
   *         - Eg: 3^20 => (3^2)^10 => 9^10 => (9^2)^5 => 81^5
   * 
   *         COMPLEXITY
   *         Time: O(log base (b)) =~ O(lob(b))
   *         Space: O(log(b))
   */
  public static int aPowerModPBetter(int a, int b, int p) {
    if (b == 0) {
      return 0;
    }

    int res = (a % p);
    if (b % 2 == 0) {
      return aPowerModPBetter(res * res, b / 2, p);
    } else {
      return a * aPowerModPBetter(res * res, b / 2, p);
    }
  }

  /**
   * Return (a ^ b) % p - optimized iterative
   * 
   * @param A
   * @param B
   * @param C
   * @return
   */
  public static int power(int A, int B, int C) {
    long res = 1L;
    long a = A;
    while (B > 0) {
      if ((B & 1) == 1) {
        res = (a % C * res % C) % C;
      }
      // Double the base
      a = (a % C * a % C) % C;
      // Half the power
      B = B >> 1;
    }
    return res < 0 ? (int) (res % C + C) : (int) (res % C);
  }

  /**
   * Finding inverse mode - brute force
   * 
   * @param a
   * @param p
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(1)
   */
  public static int inverseMod(int a, int p) {
    // (x X ?) % p = 1
    // for (int i = 0; i <= p; i++) {
    // int ans = (a % p * i % p) % p;
    // if (ans == 1) {
    // return i;
    // }
    // }
    // return 0;

    int ans = 1;
    for (int i = 1; i <= p - 2; i++) {
      // (a % M X b % M) % M
      ans = (ans % p * a % p) % p;
    }
    return ans;
  }

  /**
   * Inverse mod using Fermate's theorem
   * 
   * @param a
   * @param b
   * @return
   * 
   *         APPROACH
   *         - According to Fermates theorem
   *         \_ a^(p-2) = 1/a % p
   *         - So we take the power as (p - 2)
   */
  public static int inverseModBetter(int a, int b) {
    return aPowerModPBetter(a, b - 2, b);
  }

  /**
   * Find a pair where (A[i] + A[j]) % M == 0
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N + M)
   *         Space: O(M)
   */
  public static int modSumPairZero(int[] A, int N, int M) {
    // int ans = 0;
    // for (int i=0; i<N; i++) {
    // for (int j=0; j<N; j++) {
    // if (i == j) {
    // break;
    // }

    // if ((A[i] + A[j]) % M == 0) {
    // ans++;
    // }
    // }
    // }

    int[] count = new int[M];
    for (int i = 0; i < N; i++) {
      count[A[i] % M]++;
    }

    int ans = (count[0] * count[0] - 1) / 2;
    for (int i = 1; i <= M / 2 && i != (M - i); i++) {
      ans += count[i] * count[M - i];
    }

    if (M % 2 == 0) {
      ans += (count[M / 2] * (count[M / 2] - 1) / 2);
    }

    return ans;
  }

  public static void main(String[] args) {
    // System.out.println(trailingZerosInFactorial(100)); // 24

    // System.out.println(aPowerModP(2, 100, 20));
    // System.out.println(aPowerModPBetter(2, 100, 20));

    // System.out.println(inverseMod(3, 13));
    // System.out.println(inverseModBetter(3, 13));

    // int[] A = { 1, 4, 3, 8 }; // 2
    // System.out.println(modSumPairZero(A, A.length, 3));
    // int[] A = { 2, 7, 5, 10, 8, 4, 6, 11 }; // 5
    // System.out.println(modSumPairZero(A, A.length, 5));
    // int[] A = { 1, 3, 5, 7, 9, 4, 2, 8, 0 }; // 9
    // System.out.println(modSumPairZero(A, A.length, 4));

    // System.out.println(power(-1, 1, 20)); // 19
    System.out.println(power(2, 5, 13)); // 19
    // System.out.println(power(71045970, 41535484, 64735492)); // 20805472
  }
}
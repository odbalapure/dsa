package advance;

/**
 * ===============================
 * PROPERTIES
 * ===============================
 * 1. gcd(a, b) = gcd(b, a)
 * 2. gcd(1, a) = 1 --> only divisor on 1 is itself
 * 3. gcd(0, a) = a --> all nos. are factors of 0 and greatest divisor of a is a
 * 4. gcd(a, b-a) = gcd(a, b) --> gcd(10, 15) -> gcd(10, 15-10) -> gcd(10, 5)
 * 5. gcd(a, b) = gcd(a, b-a) --> gcd(a, b-a) -> gcd(a, b-a-a) -> gcd(a,
 * b-a-a-a)
 */

public class Day41ArraysGcd {

  /**
   * Return HCF/GCD of 2 nos.
   * 
   * @param A
   * @param B
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(min(a, b))
   *         Space: O(1)
   */
  public static int gcd(int A, int B) {
    if (A == 0 || B == 0) {
      return A + B;
    }

    int ans = 1;
    for (int i = 2; i <= Math.min(A, B); i++) {
      if (A % i == 0 && B % i == 0) {
        ans = i;
      }
    }

    return ans;
  }

  /**
   * Find gcd optimized
   * 
   * @param A
   * @param B
   * @return
   */
  public static int gcdMod(int A, int B) {
    if (A == 0) {
      return B;
    }

    return gcdMod(B % A, A);
  }

  public static void main(String[] args) {
    // System.out.println(gcd(15, 25)); // 5
    // System.out.println(gcd(20, 16)); // 4
    // System.out.println(gcd(50, 115)); // 5
    System.out.println(gcdMod(15, 25)); // 5
    System.out.println(gcdMod(20, 16)); // 4
    System.out.println(gcdMod(50, 115)); // 5
  }
}

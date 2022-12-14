package advance;

import java.util.Arrays;

public class Day42Prime {

  /**
   * Count factors of a no.
   * 
   * @param N
   * @return
   */
  public static int countFactors(int N) {
    int ft = 0;
    for (int i = 1; i <= Math.sqrt(N); i++) {
      if (N % i == 0) {
        if (i != N / i) {
          ft += 2;
        } else {
          ft += 1;
        }
      }
    }
    return ft;
  }

  /**
   * Check for prime nos. using 'Sieve of Eratosthenes'
   * 
   * @param N
   * @return
   * 
   *         COMEPLXITY
   *         Time: O(N * log(log(N)))
   *         Space: O(1)
   * 
   *         APPORACH
   *         - Create a boolean array filled with true values
   *         - Pick a no. and go over all its multiples & mark them as false
   *         - This algo turns composite number index to false
   */
  public static boolean[] primeSieve(int N) {
    boolean[] isPrime = new boolean[N + 1];
    // Fill with true values
    Arrays.fill(isPrime, true);
    // Set i = 0 & 1 as false
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i * i <= N; i++) {
      if (isPrime[i]) {
        // i = 2, j = 4, j += i (4 + 2), (6 + 2)
        for (int j = i * i; j <= N; j += i) {
          // Set composite no. as false
          isPrime[j] = false;
        }
      }
    }
    return isPrime;
  }

  /**
   * Find divisors for an input no.
   * 
   * @param N
   * @return
   */
  public static int[] countDivisors(int N) {
    return new int[0];
  }

  public static void main(String[] args) {
    // System.out.println(countFactors(10)); // 4
    // System.out.println(countFactors(60)); // 12
    // System.out.println(countFactors(100)); // 12

    System.out.println(Arrays.toString(primeSieve(9))); // [false, false, true, true, false, true, false, true, false,
                                                        // false]
  }
}

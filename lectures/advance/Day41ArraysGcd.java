package advance;

/**
 * ===============================
 * PROPERTIES
 * ===============================
 * 1. gcd(a, b) = gcd(b, a)
 * 2. gcd(1, a) = 1 --> only divisor on 1 is itself
 * 3. gcd(0, a) = a --> all nos. are factors of 0 and greatest divisor of a is a
 * 4. gcd(a, b-a) = gcd(a, b) --> gcd(10, 15) -> gcd(10, 15-10) -> gcd(10, 5)
 * 5. gcd(a, b) = gcd(a, b-a) --> gcd(a, b-a) -> gcd(a, b-a-a)
 * \_ Till b >= 0, hence we can say
 * \_ gcd(a, b) = gcd(a, b % a) [Euclidian's Theorem]
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
   * Find gcd - optimized
   * 
   * @param A
   * @param B
   * @return
   * 
   *         APPROACH:
   *         - Here we are using 2 properties
   *         - gcd(a, b) = gcd(b, a)
   *         - gcd(a, b) = gcd(a, b % a) [gcd(a, b-a-a-a-a) till a >= 0]
   * 
   *         COMPLEXITY
   *         Time: Is < O(log(b))
   *         Space: Is < O(log(b))
   */
  public static int gcdMod(int A, int B) {
    // if (A == 0)
    // return B;
    // return gcdMod(B % A, A);
    // OR
    if (B == 0)
      return A;
    return gcdMod(B, A % B);
  }

  /**
   * Find a subset with gcd == 1
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMEPLXITY
   *         Time: O(N * log(A[i]))
   *         Space: O(1)
   * 
   *         NOTE: Any subsequence is 1, then gcd of entire array is 1
   */
  public static boolean subSequenceGcd1(int[] A, int N) {
    int ans = A[0];
    for (int i = 1; i < N; i++)
      ans = gcd(ans, A[i]);
    return ans == 1;
  }

  /**
   * Find max GCD of all elements in an array after deleting 1 element
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         - Create a suffix and prefix GCD array
   *         - Iterate over A[] and do
   *         \_ ans = gcd(prefix[i-1], suffix[i+1])
   *         - Return ans
   * 
   *         Time: O(N*log(A[i]))
   *         Space: O(N)
   */
  public static int deleteOneMaxGcd(int[] A, int N) {
    int ans = 0;
    int[] prefixArr = prefixGcd(A, N);
    int[] suffixArr = suffixGcd(A, N);
    for (int i = 0; i < N; i++) {
      if (i == 0)
        ans = Math.max(ans, suffixArr[i + 1]);
      else if (i == N - 1)
        ans = Math.max(ans, prefixArr[i - 1]);
      else
        ans = Math.max(ans, gcd(prefixArr[i - 1], suffixArr[i + 1]));
    }
    return ans;
  }

  /* Create a prefix GCD array */
  public static int[] prefixGcd(int[] A, int N) {
    int[] prefixArr = new int[N];
    prefixArr[0] = A[0];
    for (int i = 1; i < N; i++) {
      prefixArr[i] = gcd(A[i], prefixArr[i - 1]);
    }
    return prefixArr;
  }

  /* Create a suffix GCD array */
  public static int[] suffixGcd(int[] A, int N) {
    int[] suffixArr = new int[N];
    suffixArr[N - 1] = A[N - 1];
    for (int i = N - 2; i >= 0; i--) {
      suffixArr[i] = gcd(A[i], suffixArr[i + 1]);
    }
    return suffixArr;
  }

  /**
   * Find min health of the last surviving player
   * In PUBG, each player has health A[i]
   * If "i" attacks "j"
   * \_ A[i] > A[j], j dies
   * \_ A[i] < A[j], A[j] =- A[i] -> j's health is reduced
   * 
   * @param A
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N * log(A[i]))
   *         Space: O(log(A[i]))
   * 
   *         OBSERVATION: Less health person should attack larger health guy
   *         If larger guy attack, the game finishes and we won't find the min
   *         health
   */
  public static int pubG(int[] A, int N) {
    int ans = A[0];
    for (int i = 1; i < N; i++)
      ans = gcd(A[i], ans);
    return ans;
  }

  public static void main(String[] args) {
    // System.out.println(gcd(15, 25)); // 5
    // System.out.println(gcd(20, 16)); // 4
    // System.out.println(gcd(50, 115)); // 5
    // System.out.println(gcdMod(15, 25)); // 5
    // System.out.println(gcdMod(20, 16)); // 4
    // System.out.println(gcdMod(50, 115)); // 5

    // int[] A = { 2, 30, 14, 72, 60 }; // false
    // int[] A = { 6, 15, 30, 10, 150 }; // true
    // System.out.println(subSequenceGcd1(A, A.length));

    // int[] A = { 9, 18, 98, 12, 30, 60 }; // 3 [9, 9, 1, 1, 1, 1] [1, 2, 2, 6, 30,
    // 60]
    // int[] A = { 20, 64, 24, 100, 50 }; // 4 [20, 4, 4, 4, 2] [2, 2, 2, 50, 50]
    // System.out.println(deleteOneMaxGcd(A, A.length));

    // int[] A = { 10, 6 }; // 2
    int[] A = { 9, 6, 15 }; // 3
    System.out.println(pubG(A, A.length));
  }
}

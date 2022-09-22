public class Day15BitManipulation {

  /**
   * =================================================================================
   * Bit wise properties
   * --------------------------------
   * a & 0 = 0
   * a & a = a
   * a | 0 = a
   * a | a = a
   * a ^ 0 = 1
   * a ^ a = 0
   * a & b == b & a
   * a ^ b = b ^ a
   * a & b & c = c & b & a = b & a & c
   * a | b | b = c | b | a = b | a | c
   * a ^ b ^ c = c ^ b ^ a = b ^ a ^ c
   * 
   * Left& Right shift
   * --------------------------------
   * int a = 10;
   * a << 1 : a x 2^1
   * a << 2 : a x 2^2
   * a << N : a X 2^N
   * 
   * For N=1, a=10 we have 10 * 2^1 = 20
   * For N=2, a=10 we have 10 * 2^2 = 40
   * 
   * int a = 1;
   * a >> 1 : a / 2^1
   * a >> 2 : a / 2^2
   * a >> N : a / 2^N
   * =================================================================================
   */

  /**
   * Check if a no. is even
   * 
   * @param N
   * @return
   * 
   *         APPROACH/IDEA
   *         - 10 & 1 = 1010 & 0001 = 0000 =~ 0
   *         - 11 & 1 = 1001 * 0001 = 0001 =~ 1
   */
  public static boolean isEven(long N) {
    return (N & 1) == 0;
  }

  /**
   * Find a element where every element appears twice except one
   * 
   * @param A
   * @param N
   * @return
   * 
   *         NOTE: XOR of 2 similar no. is 0, the element that appears will remain
   *         in the last. This code only works if the repeating elements are even
   *         in occurrence
   */
  public static int uniqueElement(int[] A, int N) {
    int ans = 0;
    for (int i = 0; i < N; i++) {
      ans ^= A[i];
    }

    return ans;
  }

  /**
   * Find power of 2 for a value of N
   * 
   * @param N
   * @return
   * 
   *         NOTE: Considering there is no overflow here.
   *         If N > 31 use 'long' data type
   */
  public static int powerOf2(int N) {
    return (1 << N);
  }

  /**
   * Find power of 5
   * 
   * @param n
   * @return
   */
  public static int powerOf5(int N) {
    // NOTE: This solution won't work use power function or loop
    // return (5 << n);

    int ans = 1;
    for (int i = 1; i <= N; i++) {
      ans *= 5;
    }

    return ans;
  }

  /**
   * Check if an ith bit is set or not
   * 
   * @param N
   * @param i
   * @return
   */
  public static boolean checkIfBitIsSet(int N, int i) {
    return ((N >> i) & 1) == 1;
  }

  public static void main(String[] args) {
    System.out.println(isEven(2)); // true

    int[] A = { 8, 1, 8, 3, 3, 0, 4, 5 }; // 0
    System.out.println(uniqueElement(A, A.length));

    System.out.println(powerOf2(10)); // 1024
    System.out.println(powerOf5(3)); // 125

    int N = 26, i = 2; // 11010 false
    System.out.println(checkIfBitIsSet(N, i));
  }
}

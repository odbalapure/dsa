package advance;

public class Day45Recursion {

  /**
   * Sum of digits of a number
   * 
   * @param N
   * @return
   */
  public static int sumOfDigits(int N) {
    if (N == 0) {
      return 0;
    }

    return N % 10 + sumOfDigits(N / 10);
  }

  /**
   * Sum of N natural numbers
   * 
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(N)
   */
  public static int sumOfNaturalNumbers(int N) {
    if (N == 1) {
      return 1;
    }

    return N + sumOfNaturalNumbers(N - 1);
  }

  /**
   * Find the Nth fibonacci number
   * 
   * @param N
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(2^N)
   *         Space: O(N)
   */
  public static int nthFibonacciNumber(int N) {
    // if (N == 1 || N == 2) {
    // return 1;
    // }
    //  
    // OR
    if (N < 2) {
      return N;
    }

    return nthFibonacciNumber(N - 1) + nthFibonacciNumber(N - 2);
  }

  public static void main(String[] args) {
    System.out.println(sumOfDigits(123));
    System.out.println(sumOfNaturalNumbers(100));
    System.out.println(nthFibonacciNumber(10));
  }
}

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

  /**
   * Print all moves in Tower of Hanoi
   * Move N discs from source - A to destination - C via B
   * 
   * @param A
   * @param B
   * @param C
   * @return
   */
  public static void towerHanoi(int N, char A, char C, char B) {
    if (N == 1) {
      System.out.println(1 + ". " + A + " -> " + C);
      return;
    }

    towerHanoi(N - 1, A, B, C);
    System.out.println(N + ". " + A + " -> " + C);
    towerHanoi(N - 1, B, C, A);
  }

  public static void main(String[] args) {
    System.out.println(sumOfDigits(123));
    System.out.println(sumOfNaturalNumbers(100));
    System.out.println(nthFibonacciNumber(10));
    towerHanoi(2, 'A', 'C', 'B');
  }
}

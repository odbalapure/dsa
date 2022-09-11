package lectures;

public class Day29Recursion {

  /**
   * Find the sum of digits for N
   * 
   * @param N
   * @return Sum of digits
   */
  public static int sumOfDigits(int N) {
    if (N <= 9 || N == 0) {
      return N;
    }

    return N % 10 + sumOfDigits(N / 10);
  }

  /**
   * Write a power function using recursion
   * 
   * @param X
   * @param N
   * @return Power of X
   */
  public static int power(int X, int N) {
    if (N == 0) {
      return 1;
    }

    return X * power(X, N - 1);
  }

  /**
   * Write a power function using recursion with less function calls
   * 
   * @param X
   * @param N
   * @return Power of X
   * 
   *         COMPLEXITY
   *         Time: O(logN)
   *         Space: O(logN)
   * 
   *         NOTE: This code will require less no. of function calls
   *         Earlier code for N=10 there were 10 function calls being made
   *         Now the function calls are being divided by 2 every time
   */
  public static int powBetter(int X, int N) {
    if (N == 0) {
      return 1;
    }

    int he = powBetter(X, N / 2);
    if (N % 2 == 1) {
      // a5 = a5/2 x a5/2 x a
      // return powBetter(X, N/2) * powBetter(X, N/2) * N;
      return he * he * X;
    } else {
      // a6 = a6/2 x a6/2
      // a6 = a6/2 x a6/2
      // return powBetter(X, N/2) * powBetter(X, N/2);
      return he * he;
    }
  }

  public static void main(String[] args) {
    // System.out.println(sumOfDigits(123)); // 6
    // System.out.println(power(3, 4)); // 81
    // System.out.println(powBetter(3, 4)); // 81
  }
}

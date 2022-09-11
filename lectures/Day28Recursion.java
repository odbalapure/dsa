package lectures;

public class Day28Recursion {

  /**
   * Find the sum of N natural nos.
   * 
   * @param N
   * @return
   */
  public static int sumNumbers(int N) {
    if (N == 1) {
      return 1;
    }

    return sumNumbers(N - 1) + N;
  }

  /**
   * Find factorial of a no.
   * 
   * @param N
   * @return
   */
  public static int factorial(int N) {
    if (N == 0) {
      return 1;
    }

    return factorial(N - 1) * N;
  }

  /**
   * Basic arithemtic code using recursion
   * 
   * @param x
   * @param y
   * @return
   */
  public static int add(int x, int y) {
    return x + y;
  }

  public static int multiply(int x, int y) {
    return x * y;
  }

  public static int subtract(int x, int y) {
    return x - y;
  }

  /**
   * Find the nth fibonacci no.
   * 
   * @param N
   * @return
   */
  public static int nthFibonacci(int N) {
    if (N == 0 || N == 1) {
      return N;
    }

    return nthFibonacci(N - 1) + nthFibonacci(N - 2);
  }

  /**
   * Print first N natural nos.
   * 
   * @param N
   */
  public static void printNumbers(int N) {
    if (N == 0) {
      return;
    }

    printNumbers(N - 1);
    System.out.print(N + " ");
  }

  /**
   * Check if a string is palindrome
   * 
   * @param str
   * @param l
   * @param r
   * @return
   */
  public static boolean isPalindrome(String str, int i) {
    if (i > str.length() / 2) {
      return true;
    }

    return str.charAt(i) == str.charAt(str.length() - i - 1) && isPalindrome(str, i + 1);
  }

  public static void main(String[] args) {
    System.out.println("Sum of numbers: " + sumNumbers(5));
    System.out.println("Factorial of a number: " + factorial(6));
    System.out.println("Arithmetic answer: " + subtract(multiply(add(10, 20), 30), 75));
    System.out.println("Nth fibonacci number: " + nthFibonacci(4));
    printNumbers(10);
    System.out.println("\nIs palindrome: " + isPalindrome("kayak", 0));
  }
}

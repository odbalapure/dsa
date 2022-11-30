package advance;

public class Day46RecursionSorting {

  /**
   * Sum of digits of N
   * 
   * @param N
   * @return
   */
  public static int sumDigitN(int N) {
    if (N == 0)
      return 0;
    return N % 10 + sumDigitN(N / 10);
  }

  static boolean isMagic = true;

  /**
   * Check if a given no. is a magic no.
   * Where sum of all the digits is 1
   * 
   * @param N
   * @return
   */
  public static boolean magicNumber(int N) {
    // if (N <= 9)
    // return N == 1 ? true : false;
    // return magicNumber(sumDigitN(N));
    return N % 9 == 1 ? true : false;
  }

  /**
   * Generate all valid paranthese of length 2N
   * 
   * @param N
   */
  public static void generateAllValidParenteses(int N, StringBuilder str, int open, int close) {
    if (open + close == 2 * N) {
      System.out.println(str);
      return;
    }
    if (open < N)
      generateAllValidParenteses(N, str.append('('), open + 1, close);
    if (close < open)
      generateAllValidParenteses(N, str.append(')'), open, close + 1);
  }

  public static void main(String[] args) {
    // System.out.println(sumDigitN(123)); // 6
    // System.out.println(magicNumber(83557)); // true
    generateAllValidParenteses(2, new StringBuilder(""), 0, 0);
  }
}

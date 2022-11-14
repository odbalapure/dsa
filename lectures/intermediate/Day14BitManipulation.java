package intermediate;

public class Day14BitManipulation {

  /**
   * =================================================================================
   * Ocalt to Decimal
   * --------------------------------
   * 125
   * (1 X 8^2 + 2 X 8^1 + 5 X 8^0) => 64 + 16 + 5 => 85
   * NOTE: 1001 is also an ocatal number since octal range is [0 to 8]
   * 
   * 
   * Ternary to Deciaml
   * --------------------------------
   * 02101
   * (0 X 3^4 + 2 X 3^3 + 1 X 3^2 + 0 X 3^1 + 1 X 3^0) = 64
   * 
   * 
   * Binary to Decimal
   * --------------------------------
   * 1111
   * (1 X 2^3 + 1 X 2^2 + 1 X 2^1 + 1 X 2^0) = 15
   * 
   * 
   * Decimal to Binary
   * --------------------------------
   * 28
   * 28 % 2 = 0 | 14
   * 14 % 2 = 0 | 7
   * 7 % 2 = 1 | 3
   * 3 % 2 = 1 | 1
   * 1 % 2 = 1 | 1
   * 
   * NOTE: Collect the answer backwards: 11100
   * 
   * 
   * Adding decimal numbers
   * --------------------------------
   * 2 4 5 8
   * 3 7 6 4
   * -------
   * 6 2 2 2
   * 
   * value 2 (remainder) = 12 (sum) % 10
   * carry 1 (quotient) = 12 (sum) / 10
   * 
   * 
   * Left most bit & -ve numbers
   * --------------------------------
   * - Left most bit is the MOST SIGNIFICANT BIT
   * - The value of left most bit >>> sum of all the bits on the right side
   * - To get -ve binary no. we need 2's compliment
   * \_ Flip all the bits
   * \_ Add 1 to the resultant no.
   * 
   * 
   * Range N bit no. can store
   * --------------------------------
   * - [-2^(N-1) to 2^(N-1) - 1]
   * - Data types
   * \_ Byte - 1 byte - 8 bits [-128 to 127]
   * \_ Short - 2 byte - 16 bits [-2^15 to 2^15 - 1]
   * \_ Int - 4 byte - 32 bits [-2^31 to 2^31 - 1]
   * \_ Long - 8 byte - 64 bits [-2^63 to 2^63 - 1]
   * =================================================================================
   */

  /**
   * Deciaml to binary conversion
   * 
   * @param N
   * @return
   */
  public static int binaryToDecimal(long N) {
    int res = 0, pow = 0;

    while (N > 0) {
      long dig = N % 10;
      // 101 => 1 X 2^2 + 0 X 2^1 + 1 X 2^0
      // NOTE: Using a power function will increase the T.C. for large powers
      // Bit Manipulation will find the power in O(1) time
      // res += dig * Math.pow(2, pow++);
      res += dig * (1 << pow++);
      N /= 10;
    }

    return res;
  }

  /**
   * Convert deciaml no. string to a binary no. string
   * 
   * @param N
   * @return
   */
  public static String decimalToBinary(int N) {
    StringBuilder str = new StringBuilder("");

    while (N > 0) {
      int rem = N % 2;
      str.append(rem);
      N /= 2;
    }

    return str.reverse().toString();
  }

  /**
   * Convert decimal no. to binary and reverse it and return it as a 32 bit
   * integer
   * 
   * For N = 1
   * Binary will be = 00000000000000000000000000000001
   * Final result will be = 10000000000000000000000000000000
   * 
   * @param N
   * @return
   */
  public static String reverse32BitInteger(int N) {
    String str = decimalToBinary(N);
    StringBuilder zeros = new StringBuilder("");

    for (int i = 0; i < 32 - str.length(); i++) {
      zeros.append('0');
    }

    zeros.append(str);
    return zeros.reverse().toString();
  }

  /**
   * Add 2 binary strings
   * 
   * @param A
   * @param B
   * @return
   */
  public static String addBinaryStrings(String A, String B) {
    StringBuilder str = new StringBuilder("");

    int i = A.length() - 1, j = B.length() - 1, carry = 0;
    while (i >= 0 || j >= 0) {
      int sum = 0;
      if (j >= 0) {
        sum += B.charAt(j--) - '0';
      }

      if (i >= 0) {
        sum += A.charAt(i--) - '0';
      }

      str.append(sum % 2);
      // NOTE: If the no. was decimal we would divide by 10
      carry = sum / 2;
    }

    if (carry != 0) {
      str.append(carry);
    }

    return str.reverse().toString();
  }

  public static void main(String[] args) {
    System.out.println(binaryToDecimal(1111)); // 15
    System.out.println(addBinaryStrings("100", "11")); // 111
    System.out.println(decimalToBinary(15)); // 1111
    System.out.println(reverse32BitInteger(3)); // 11000000000000000000000000000000
  }
}

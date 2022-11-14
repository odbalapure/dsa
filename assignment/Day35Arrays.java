import java.util.Arrays;

public class Day35Arrays {

  /**
   * 
   * @param A
   * @param N
   * @return
   */
  public static int[] addOne(int[] A, int N) {
    if (A.length == 1 && A[0] == 0) {
      A[0] = 1;
      return A;
    }

    int sum = 0;
    int carry = 0;

    if ((A[N - 1] + 1) > 9) {
      carry = 1;
      for (int i = N - 1; i >= 0; i--) {
        sum += A[i] + carry;
        A[i] = (sum % 10);
        carry = sum / 10;

        if (carry == 0) {
          break;
        }
      }

      if (carry != 0) {
        int[] newArr = new int[N + 1];
        newArr[0] += 1;
        return newArr;
      }

      return A;
    } else {
      A[N - 1] += 1;
      return A;
    }
  }

  public static void main(String[] args) {
    int[] A = { 1, 9, 9, 9, 9, 9 };
    System.out.println(Arrays.toString(addOne(A, A.length)));
  }
}

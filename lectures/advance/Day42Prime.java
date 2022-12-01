package advance;

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

  public static void main(String[] args) {
    System.out.println(countFactors(10)); // 6
    System.out.println(countFactors(60)); // 12
  }
}

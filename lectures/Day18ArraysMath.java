package lectures;

public class Day18ArraysMath {
  /**
   * Toggle doors and find the no. of open doors at the end
   * 
   * @param arr
   * @param N
   * @return Return no. of open doors
   *
   *         Person 1 toogles/opens all doors
   *         Person 2 closes 2, 4, 8, ... , N doors
   *         Person 3 closes 3 and 9 and opens 6
   *         .
   *         .
   *         .
   *         Person N closes doors that are open and vice versa
   *         Eg: For doors (1 - 10) the answers is 1, 4, 9
   *
   *         NOTE: For perfect squares the doors will be open
   *         Gate with odd no. of factors remain OPEN
   *         Gate with even no. of factors remain CLOSE
   * 
   *         COMPLEXITY
   *         Time: O(N * sqrt(N))
   *         Space: O(1)
   */
  public static int toggleDoor(int[] arr, int N) {
    int open = 0;
    for (int i = 0; i < N; i++) {
      if (isPerfectSquare(arr[i])) {
        open++;
      } else {
        continue;
      }
    }

    return open;
  }

  /* Check if a no. is a perfect square */
  public static boolean isPerfectSquare(int x) {
    if (x >= 0) {
      int sqRoot = (int) Math.sqrt(x);
      return ((sqRoot * sqRoot) == x);
    }

    return false;
  }

  /**
   * Given no. "n" return a magical no.
   * 
   * @param arr
   * @param N
   * @return
   *
   *         Magical no: A no. that can be represented as a sum of unique powers
   *         of 5 with power > 0
   *         1 => 5^1 => 5
   *         2 => 5^2 => 25
   *         5 => 5^3 + 5^1 => 130
   *         6 => 5^3 + 5^2 => 130
   * 
   * 
   *         3 2 1
   *         ---------------
   *         0 0 5^1
   *         0 5^2 0
   *         0 5^2 5^1
   */
  public static int nthMagicalNo(int N) {
    int res = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= i; j++) {
        res += Math.pow(5, j);
      }
    }

    return res;
  }

  /**
   * Find majority element
   * 
   * @param A
   * @param N
   * @return
   */
  public static int majorityElement(int[] A, int N) {
    int indx = 0, maxCount = 0;
    for (int i = 0; i < N; i++) {
      int count = 0;
      for (int j = 0; j < N; j++) {
        if (A[i] == A[j]) {
          count++;
        }

        if (count > maxCount) {
          maxCount = count;
          indx = i;
        }
      }
    }

    if (maxCount > N / 2) {
      return A[indx];
    }

    return -1;
  }

  /**
   * Find a majority element - no extra space and no sorting
   * NOTE: There cannot be more than 1 majority element
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPORACH
   *         - Use Moore's voting algorithm, it works in 2 phases
   *         - In 1st phase a candidate is selected, 2nd phase confirms for the
   *         majority element
   *         - 2nd phase is not needed if we can gurantee that a majority is
   *         always present
   */
  public static int majorityElementBetter(int[] A, int N) {
    int ans = 0, count = 1;

    // Part 1: Find a candidate element
    for (int i = 1; i < N; i++) {
      if (A[ans] == A[i]) {
        count++;
      } else {
        count--;
      }

      if (count == 0) {
        ans = i;
        count = 1;
      }
    }

    // Part 2: Confirm that candidate is a majority element
    count = 0;
    for (int i = 0; i < N; i++) {
      if (A[ans] == A[i]) {
        count++;
      }
    }

    if (count <= N / 2) {
      ans = -1;
    }

    return ans;
  }

  /**
   * Josephus problem - where to stand inorder to be the last survior
   * 
   * @param A
   * @param N
   * 
   *          When no. of survivors are equal to power of 2, the man will be
   *          holding the sword lives
   *          N = 11, 7 lives
   *          N = 13, 11 lives
   * 
   *          APPROACH
   *          - Reach a no. of 2^k from the last i.e. N
   *          - Kill "x" no. of people to reach 2^k
   *          - So, x = N - 2^k
   *          - So the pattern is like 2 X 1 + 1, 2 X 2 + 1, 2 X 3 + 1...
   *          - Eg: N=13, 2^k=8, x=5
   *          - So, Find nearest power of 2 from N
   *          - Go on until we have, x = N - 2^k
   *          - Position wil be 2 * x + 1
   */
  public static int josephusProblem(int N) {
    int pow2k = 0;
    
    for (int i = N; i >= 1; i--) {
      if (isPowerOf2(i)) {
        pow2k = i;
        break;
      }
    }

    int x = N - pow2k;
    return 2 * x + 1;
  }

  /* Check if no. is power of 2 */
  public static boolean isPowerOf2(int N) {
    if (N == 2) {
      return true;
    }

    if (N == 0) {
      return false;
    }

    while (N != 1) {
      if (N % 2 != 0) {
        return false;
      }

      N /= 2;
    }

    return true;
  }

  public static void main(String[] args) {
    // int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    // System.out.println(toggleDoor(arr, arr.length));

    // System.out.println(nthMagicalNo(2)); // 2 25, 5 130

    // int[] A = { 3, 3, 4, 2, 4, 4, 2, 4, 4 }; // 4
    // System.out.println(majorityElement(A, A.length));

    // System.out.println(josephusProblem(13)); // 11 7, 13 11
  }
}

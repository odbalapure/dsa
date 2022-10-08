package intermediate;

public class Day16BitManipulation {

  /**
   * =================================================================================
   * Power of 2^N
   * --------------------------------
   * If a no. is power of 2, then (N & (N - 1)) == 0
   * Eg:
   * 32 -> 100000
   * 31 -> 011111
   * Performing & operation the result is 0
   * 
   * 
   * =================================================================================
   */

  /**
   * Add x set bits and y unset bits
   * Eg: x=2 (11), y=3(000) => 11000 => 24
   * 
   * @param x
   * @param y
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(x)
   *         Space: O(1)
   */
  public static int addXsetYunsetBits(int x, int y) {
    // int ans = 0;

    // while (x > 0) {
    // ans += (1 << (x + y - 1));
    // // OR
    // // ans |= (1 << (x + y - 1));
    // x--;
    // }

    // return ans;
    // This is O(1) work instead of O(x)
    return (((1 << x) - 1) << y);
  }

  /**
   * Find the max consecutive 1s by replacing a single 0
   * 
   * @param A
   * @param N
   * @return
   * 
   *         APPROACH
   *         For each 0
   *         - Count of 1s on left side
   *         - Count of 1s on right side
   *         - total = lc + rc + 1
   *         - ans = max(ans, total)
   * 
   *         NOTE
   *         - Complete array if 0 - return 1
   *         - Complete array if 1 - return N
   */
  public static int maxConsecutive1sReplace0(int[] A, int N) {
    int ans = 0;
    for (int i = 0; i < N; i++) {
      if (A[i] == 0) {
        int lc = 0, rc = 0;

        // Get left count
        for (int j = i - 1; j >= 0; j--) {
          if (A[j] == 1) {
            lc++;
          } else {
            break;
          }
        }

        // Get right count
        for (int j = i + 1; j < N; j++) {
          if (A[j] == 1) {
            rc++;
          } else {
            break;
          }
        }

        ans = Math.max(ans, rc + lc + 1);
      }
    }

    // If entire A[] is 0 then result is 1
    // If entire A[] is 1 then result is A.length
    if (ans == 0) {
      return N;
    }

    return ans;
  }

  /**
   * Swap atmost one 0 and find the max. consecutive 1s
   * 
   * @param A
   * @param N
   * @return
   * 
   *         NOTE: The max no. of consecutive 1s cannot be > total no. of 1s
   * 
   *         APPROACH
   *         - Find count of 1
   *         - For every 0 find left and right count
   *         - If (count of 1s) > (left count + right count))
   *         \_ ans = max(ans, left count + right count + 1)
   *         - Else
   *         \_ ans = max(ans, left count + right count)
   */
  public static int maxConsecutive1sSwap0(int[] A, int N) {
    int c1 = 0, ans = 0;

    for (int i = 0; i < N; i++) {
      c1 += (A[i] == 1) ? 1 : 0;
    }

    for (int i = 0; i < N; i++) {
      int lc = 0, rc = 0;

      if (A[i] == 0) {
        // Get left count
        for (int j = i - 1; j >= 0; j--) {
          if (A[j] == 1) {
            lc++;
          } else {
            break;
          }
        }

        // Get right count
        for (int j = i + 1; j < N; j++) {
          if (A[j] == 1) {
            rc++;
          } else {
            break;
          }
        }

        if (c1 > (lc + rc)) {
          // For [0, 0, 1, 1, 0, 1, 1, 1, 0, 1]
          // Total 1s = 6
          // For i=4, we have c1 > (lc + rc), now there exists an extra 1 to swap
          // somewhere in the array
          ans = Math.max(ans, lc + rc + 1);
        } else {
          // For [0, 0, 1, 1, 0, 1, 1, 1, 0, 0]
          // Total 1s = 5
          // For i=4, we have c1 = (lc + rc), means we don't have an extra 1 to bring
          // from somewhere in the array
          ans = Math.max(ans, lc + rc);
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    // int x = 2, y = 3; // 11000 -> 24
    // System.out.println(addXsetYunsetBits(x, y));

    // int[] A = { 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 }; // i=4 | 6
    // int[] A = { 1, 1, 1, 1, 1, 1, 1, 1 }; // 8
    // int[] A = { 0, 0, 0, 0, 0, 0, 0, 0 }; // 1
    // System.out.println(maxConsecutive1sReplace0(A, A.length));

    // int[] A = { 1, 1, 0, 1, 1, 1, 0, 1, 1 }; // 6
    // int[] A = { 0, 0, 1, 1, 0, 1, 1, 0 }; // 4
    // int[] A = { 0, 0, 1, 1, 0, 1, 1, 1, 0, 0 }; // 5
    int[] A = { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1 }; // 6
    System.out.println(maxConsecutive1sSwap0(A, A.length));
  }
}

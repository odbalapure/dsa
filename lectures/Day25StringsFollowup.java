public class Day25StringsFollowup {
  /**
   * Reverse every word in a string (Constant Space)
   * 
   * @param str
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N) + O(N) =~ O(N)
   *         Space: O(1)
   */
  public static String reverseWordsInSentence(String str) {
    char[] sentence = reverse(str.toCharArray(), 0, str.length() - 1);

    int l = 0;
    for (int r = 0; r < str.length(); r++) {
      if (sentence[r] == ' ') {
        reverse(sentence, l, r - 1); // O(r - l)
        l = r + 1;
      } else {
        r += 1;
      }
    }

    reverse(sentence, l, str.length() - 1);
    return new String(sentence);
  }

  /* Helper function to reverse a string */
  public static char[] reverse(char[] ch, int i, int j) {
    for (; i <= j; i++, j--) {
      char temp = ch[i];
      ch[i] = ch[j];
      ch[j] = temp;
    }

    return ch;
  }

  /**
   * Find length of the longest palindromic substring
   * 
   * @param str
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N^2)
   *         Space: O(1)
   */
  public static int longestPalindromeSubstring(String str) {
    int ans = 0;
    for (int i = 0; i < str.length(); i++) {
      int odd = getLength(str, i, i);
      int even = getLength(str, i, i + 1);
      ans = Math.max(odd, ans);
      ans = Math.max(even, ans);
    }

    return ans;
  }

  /* Helper function that returns the length of palindromic string */
  public static int getLength(String str, int l, int r) {
    int len = 0;
    // Adjust the indices incase
    // substring is of odd length
    if (l == r) {
      len = 1;
      l -= 1;
      r += 1;
    }

    while (l >= 0 && r <= str.length() - 1) {
      if (str.charAt(l) != str.charAt(r)) {
        break;
      }

      l -= 1;
      r += 1;
      len += 2;
    }

    return len;
  }

  public static void main(String[] args) {
    // String str = "The sky is blue";
    // System.out.println(reverseWordsInSentence(str)); // blue is The sky
    String str = "crulgzfkif gg ombt vemmoxrgf qoddptokkz op xdq hv "; // hv xdq op qoddptokkz vemmoxrgf ombt gg
                                                                       // crulgzfkif
    System.out.println(reverseWordsInSentence(str));

    // String str = "abacab"; // 5
    // String str = "sunabbas"; // 6
    // System.out.println(longestPalindromeSubstring(str));
  }
}

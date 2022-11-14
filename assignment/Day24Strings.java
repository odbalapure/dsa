import java.util.ArrayList;
import java.util.Collections;

public class Day24Strings {

  /**
   * Find min no. of characters to be replaced to reduce the no. of distinct
   * characters
   * 
   * @param A
   * @param B
   * @return
   */
  public static int replaceToMinimizeDistinctChar(String A, int B) {
    int count[] = new int[26];

    for (int i = 0; i < A.length(); ++i) {
      count[A.charAt(i) - 'a']++;
    }

    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 26; ++i) {
      if (count[i] > 0) {
        list.add(count[i]);
      }
    }

    Collections.sort(list);

    for (int i = 0; i < list.size(); ++i) {
      B -= list.get(i);
      if (B < 0) {
        return list.size() - i;
      }
    }

    return 1;
  }

  /**
   * Check if the string alpha numeric
   * 
   * @param str
   * @return
   */
  public static boolean isAplhaNumeric(String str) {
    for (int i = 0; i < str.length(); i++) {
      if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
          || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
          || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
        continue;
      } else {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(replaceToMinimizeDistinctChar("abcabbccd", 3)); // 2
    System.out.println(isAplhaNumeric("a#sbasda")); // false
  }
}

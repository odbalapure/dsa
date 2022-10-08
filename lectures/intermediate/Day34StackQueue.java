package intermediate;

import java.util.Stack;

public class Day34StackQueue {

  /**
   * Check if brackets are balanced in an expression
   * 
   * @param expr
   * @return
   */
  public static boolean areBracketsBalanced(String expr) {
    Stack<Character> st = new Stack<>();

    for (int i = 0; i < expr.length(); i++) {
      char ch = expr.charAt(i);

      if (ch == '(' || ch == '{' || ch == '[') {
        st.push(ch);
      } else if (ch == ')') {
        if (!handleBrackets(st, '(')) {
          return false;
        }
      } else if (ch == '}') {
        if (!handleBrackets(st, '{')) {
          return false;
        }
      } else if (ch == ']') {
        if (!handleBrackets(st, '[')) {
          return false;
        }
      }
    }

    return st.isEmpty();
  }

  /* Helper function to compare brackets */
  public static boolean handleBrackets(Stack<Character> st, char openBracket) {
    if (st.size() == 0) {
      return false;
    } else if (st.peek() != openBracket) {
      return false;
    } else {
      st.pop();
      return true;
    }
  }

  public static void main(String[] args) {

  }
}

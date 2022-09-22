/* Tree Node */
class TreeNode {
  int val;
  TreeNode left, right;

  TreeNode(int val) {
    this.val = val;
  }
}

public class Day32Trees {

  /**
   * Count the nodes in a binary tree
   * 
   * @param A
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(H) | [Height of tree]
   * 
   *         NOTE:
   *         - TC is O(N) since constant work is done for each node
   *         - No. of functions in the call stack ∝ Height of the binary tree
   *         - Eg: There will be 4 function (height + 1) calls for a tree of
   *         height 3
   */
  public static int countNodes(TreeNode A) {
    if (A == null) {
      return 0;
    }

    return 1 + countNodes(A.left) + countNodes(A.right);
  }

  /**
   * Find the max value in a binary tree
   * 
   * @param A
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(N)
   *         Space: O(H) | [Height of tree]
   */
  public static int maxValue(TreeNode A) {
    if (A == null) {
      return Integer.MIN_VALUE;
    }

    return Math.max(A.val, Math.max(maxValue(A.left), maxValue(A.right)));
  }

  /**
   * Find the height of a binary tree
   * 
   * @param A
   * @return
   */
  public static int heigthOfTree(TreeNode A) {
    if (A == null) {
      return 0;
    }

    return Math.max(heigthOfTree(A.left), heigthOfTree(A.right)) + 1;
  }

  /**
   * Print leaf nodes of a binary tree
   * 
   * @param A
   */
  public static void printLeaves(TreeNode A) {
    if (A == null) {
      return;
    }

    printLeaves(A.left);

    if (A.left == null && A.right == null) {
      System.out.print(A.val + " ");
    }

    printLeaves(A.right);
  }

  /**
   * Print leaf nodes of a binary tree
   * 
   * @param A
   */
  public static int countLeaves(TreeNode A) {
    if (A == null) {
      return 0;
    }

    if (A.left == null && A.right == null) {
      return 1;
    }

    return countLeaves(A.left) + countLeaves(A.right);
  }

  public static void main(String[] args) {
    TreeNode A = new TreeNode(10);
    A.left = new TreeNode(8);
    A.right = new TreeNode(30);
    A.right.left = new TreeNode(40);
    A.right.right = new TreeNode(50);

    System.out.println(countNodes(A)); // 5
    System.out.println(maxValue(A)); // 50
    System.out.println(heigthOfTree(A)); // 3
    printLeaves(A); // 8 40 50
    System.out.println();
    System.out.println(countLeaves(A)); // 3
  }
}

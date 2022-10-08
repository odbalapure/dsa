package intermediate;

/* Linked List Node */
class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
  }
}

public class Day31LinkedList {

  /**
   * Check if a node exists in a linked list
   * 
   * @param head
   * @param k
   * @return
   * 
   *         COMPLEXITY
   *         Time: O(K)
   *         Space: O(1)
   */
  public int findNode(Node head, int k) {
    Node temp = head;
    for (int i = 0; i < k; i++) {
      if (temp == null) {
        return -1;
      }

      temp = temp.next;
    }

    return temp != null ? temp.data : -1;
  }

  public static void main(String[] args) {
    Day31LinkedList ll = new Day31LinkedList();
    Node node = new Node(0);
    node.next = new Node(1);
    node.next.next = new Node(2);
    node.next.next.next = new Node(3);

    System.out.println(ll.findNode(node, 5));
  }
}

/**
 * DS_LinkedList
 * My immplementation of a singly linked list.
 * 
 * @author github.com/jbarry302
 * @since 2023/5/19
 */
public class DS_LinkedList<T> {

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            if (data == null)
                return "null";
            return data.toString();
        }
    }

    private Node<T> head;
    private int size;

    public DS_LinkedList() {
        head = null;
    }

    public void insert(T val) {
        ++this.size;
        Node<T> newNode = new Node<T>(val);
        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        if (head == null)
            return "[]";

        StringBuilder sb = new StringBuilder("[");
        Node<T> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        return sb.append("]").toString();
    }

    // public static void x(String s, Object... o) {
    //     System.out.printf(s + "\n", o);
    // }
    // public static void main(String[] args) {
    //     DS_LinkedList<String> list = new DS_LinkedList<String>();
    //     x("isEmpty: %b", list.isEmpty());
    //     list.insert("A");
    //     list.insert("B");
    //     list.insert("C");
    //     list.insert("D");
    //     list.insert("E");
    //     x("%s\nsize: %d, isEmpty: %b", list, list.size(), list.isEmpty());
    // }

}

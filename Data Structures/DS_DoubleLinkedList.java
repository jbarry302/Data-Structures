public class DS_DoubleLinkedList<T> {

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

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
    private Node<T> tail;
    private int size;

    public DS_DoubleLinkedList() {
        head = null;
        tail = null;
    }

    public void insert(T data) {
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr.next.prev = curr;
                curr = curr.next;
            }
            curr.next = new Node<T>(data);
            curr.next.prev = curr;
            tail = curr.next;
        }
        ++this.size;
    }

    public void insertFirst(T data) {
        ++this.size;
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> curr = new Node<T>(data);
            curr.next = head;
            head.prev = curr;
            head = curr;
        }
    }

    public void insertLast(T data) {
        if(head == null) {
            throw new NullPointerException("Null linkedlist, insert from head first");
        } else if(tail == null) {
            ++this.size;
            Node<T> curr = new Node<T>(data);
            head.next = curr;
            curr.prev = head;
            tail = curr;
            return;
        }

        ++this.size;
        Node<T> curr = new Node<T>(data);
        tail.next = curr;
        curr.prev = tail;
        tail = curr;
    }

    public boolean deleteNode(T data) {
        Node<T> curr = head;
        if(this.size == 1) {
            if(head.data.equals(data)) {
                head = null;
                tail = null;
                --this.size;
                return true;
            }
            return false;
        } else if (head.data.equals(data)) {
            head.next.prev = null;
            head = head.next;
            --this.size;
            return true;
        } else if (tail.data.equals(data)) {
            tail.prev.next = null;
            tail = tail.prev;
            --this.size;
            return true;
        }
        while (curr != null) {
            if (curr.data.equals(data)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                curr = null;
                --this.size;
                return true;
            }
            curr = curr.next;
        }
        return false;
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
    //     DS_DoubleLinkedList<Integer> list = new DS_DoubleLinkedList<Integer>();
    //     list.insert(1);
    //     list.insert(2);
    //     list.insert(3);
    //     list.insert(4);
    //     list.insert(5);

    //     x("List: %s", list);
    //     x("Size: %d", list.size());
    //     x("Delete 3: %b", list.deleteNode(3));
    //     x("List: %s", list);
    //     x("Size: %d", list.size());
    //     x("Delete 1: %b", list.deleteNode(1));
    //     x("List: %s", list);
    //     x("Size: %d", list.size());
    //     x("Delete 5: %b", list.deleteNode(5));
    //     x("List: %s", list);
    //     x("Size: %d", list.size());
    //     x("Delete 2: %b", list.deleteNode(2));
    //     x("List: %s", list);
    //     x("Size: %d", list.size());
    //     x("Delete 4: %b", list.deleteNode(4));
    //     x("List: %s", list);
    //     x("Size: %d", list.size());
    // }

}

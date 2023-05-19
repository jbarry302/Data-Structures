public class BinaryTree {

    static class Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    static class Tree {
        Node root;

        static void s(String x, Object... a) {
            System.out.printf(x + "\n", a);
        }

        public void insert(int val) {
            if(root == null) {
                root = new Node(val);
                s("initial insert: %d", val);
            } else {
                Node child = root;
                while(true) {
                    if(val >= child.val) { 
                        child = child.right;
                    } else {
                        child = child.left;
                    }
                    if(child == null) {
                        child = new Node(val);
                        s("insert next: %d", val);
                        return;
                    }
                }
                
            }
        }

        public void display(Node node) {
            if(node == null)  return;

            System.out.print(node.val + " ");

            display(node.left);
            display(node.right);
        }
    }


    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);

        t.display(t.root);
        System.out.println("done");
    }

}

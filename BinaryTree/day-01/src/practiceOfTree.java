import org.omg.CORBA.TRANSACTION_MODE;

//
public class practiceOfTree {

    public class TreeNode {
        int value;
        tree.TreeNode left;
        tree.TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.value = val;
        }
        TreeNode(int val, tree.TreeNode left, tree.TreeNode right) {
            this.value = val;
            this.left = left;
            this.right = right;
        }
    }



    //设计一个打印二叉树的打印函数
    //思路一：把二叉树填满，再打印
    public static void printTree (TreeNode head) {
        System.out.println("Binary Tree");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }
    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^" ,len);
    }
    public  static void dijkstra(int num){
        int dist[100];
        dist[num] = 0;
        int Q[1000];
        int hh = 0, tt = 0;
        while(hh <= tt){
            auto t = q[hh++];
            for(int i = 0; i < 4; i++){
                int a = x + dx[i], b = y + dy[i];

            }
        }
    }
    public static void getSpace(int num){

    }






}














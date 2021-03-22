//代码格式化快捷键ctrl + alt + L


//某二叉树节点结构如下  讲解：https://www.bilibili.com/video/BV1dV411h7v6?p=27&t=881
//class Node{
//    int value;
//    Node left;
//    Node right;
//    Node parent;
//}
//给你二叉树中的某个节点（记为x），返回该节点的后继节点
//后继节点就是在中序遍历中位于该节点后面的节点

//普遍解法：沿着parent一直找到该树的头节点，然后中序遍历；（有些麻烦，复杂度O(N)）
//按理说是可以从当前节点直接到达其后序节点的
//如果x有右子树，那么x的后序一定是其右子树最左边的节点
//如果x没有右子树，一直往上找，如果是上一个节点的右孩子就继续往上找，知道找到是其父的左孩子为止，该父就是x的后继节点，
//如果一直没有找到，那么x就是整棵树的最右节点，没有后继
//上一行的原由：如果某个节点的左子树最右边节点是x,那么该节点就是x的后继节点

public class binTreeOne {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else { //无右子树
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }//如果是整颗树的最右节点，返回空
            return parent;
        }

    }

    //找到最左节点
    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}

//镜像问题：找某个节点的前驱节点


























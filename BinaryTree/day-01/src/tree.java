import org.omg.CORBA.TRANSACTION_MODE;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class tree {

    public class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.value = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.value = val;
            this.left = left;
            this.right = right;
        }
    }

    //前序遍历 - 递归方式
    public static void pre(TreeNode head) {
        if(head == null) {
            return ;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    //中序遍历 - 递归
    public static void in(TreeNode head) {
        if(head == null) {
            return ;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    //后序遍历 - 递归
    public static void pos(TreeNode head) {
        if(head == null) {
            return ;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    //先序 - 非递归
    public static void noRecursionPre(TreeNode head) {
        System.out.println("pre-order");
        if(head != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //后序 - 非递归 方法一
    public static void noRecursionPos1(TreeNode head) {
        System.out.println("pos-order");
        if (head != null) {
            Stack<TreeNode> s1 = new Stack<TreeNode>();
            Stack<TreeNode> s2 = new Stack<TreeNode>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left ！= null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    //后序 - 非递归 方法二(炫技版，只用一个栈实现)  看视频讲解55：00 min
    public static void noRecursionPos2(TreeNode h) {
        System.out.println("pos - order: ");
        if (h != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(h);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();//只是让c指向栈顶节点，并不弹出栈顶节点
                if (c.left != null && h != c.left && h != c.right) {//左树没处理，处理左树去
                    stack.push(c.left);
                } else if (c.right != null && h ！= c.right) {//右树没处理，处理右树去
                    stack.push(c.right);
                } else {//左右树都处理完了，打印当前，并让h指向刚打印的节点
                    System.out.println(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

    //中序 - 非递归
    public static void noRecursionIn (TreeNode head) {
        System.out.println("in - order");
        if (head != null) {
            Stack<TreeNode> stack = Stack<TreeNode>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) { //先把整颗树的左边界节点全部压到栈里去
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }

        System.out.println();
    }


    //层次遍历  广度优先  队列
    //层次遍历 并发现每一层的结束  参考LeetCode 102  107
    public static void level(TreeNode head) {
        if (head == null) {
            return ;
        }
        Queue<TreeNode> queue = new Queue<TreeNode>();
        queue.add(head);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();//弹出让cur指向该节点
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    //序列化 - 先序
    public static Queue<String> preSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }
    public static void pres(TreeNode head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right,ans);
        }
    }

    //反序列化 - 利用先序得到的序列建树
    public static TreeNode buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);
    }
    public static TreeNode preb(Queue<String> prelist) {
        String value = prelist.poll();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }


    //序列化 - 层次遍历
    //层次遍历和序列化同步进行
    public static Queue<String> levelSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            Queue<TreeNode> queue = new LinkedList<TreeNode>()；
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                } else {//无论是不是为空，都要加入ans序列化
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //反序列化 - 利用层次遍历得到的序列还原树
    public static TreeNode buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        TreeNode head = generateTreeNode(levelList.poll());
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (head != null) {
            queue.add(head);
        }
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateTreeNode(levelList.poll());
            node.right = generateTreeNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static void generateTreeNode


}

















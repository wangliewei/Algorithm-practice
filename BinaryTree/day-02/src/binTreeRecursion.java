//https://www.bilibili.com/video/BV1dV411h7v6?p=27&t=881   59:00 min

import java.util.ArrayList;
import java.util.List;

public class binTreeRecursion {


    //第一题
//给定一颗二叉树的头节点，返回这颗二叉树是不是平衡二叉树
//平衡二叉树：该树的左子树平衡并且右子树平衡并且左右子树的高度差不超过1；
    public class Node {
        int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static boolean isBalanced2(Node head) {
        return process2(head).isBalanced;
    }

    //左、右要求都一样，Info 信息返回的结构体
    public static class Info {
        public boolean isBalanced; //是否平衡
        public int height; //最大高度

        public Info(boolean b, int h) {
            isBalanced = b;
            height = h;
        }
    }

    public static Info process2(Node X) {
        if (X == null) {//空树是平衡的，高度为0
            return new Info(true, 0);
        }
        Info leftInfo = process2(X.left);
        Info rightInfo = process2(X.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height)) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }


//第二题
//给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
/*
1)求以X为根节点的树的最大距离，如果与X无关，要么是左树的最大距离，要么是右树的最大距离，返回最大的即可
2）如果与X有关，即最大距离路径通过X，那么一定是从X左树距离X最远的点到X右树中距离X最远的点，
   左树最远的点到X距离即左树的高度，右树同理；左树高度 + 1 + 右树高度

 */

    public static int maxDistance2(Node head) {
        return process(head).maxDistance;
    }

    public static class Info2 {
        public int maxDistance; //最大距离
        public int height; //最大高度

        public Info2 (int dis, int h){
            maxDistance = dis;
            height = h;
        }
    }

    public static Info2 process(Node X) {
        if(X == null) {
            return new Info2(0, 0);
        }
        Info2 leftInfo = process(X.left);
        Info2 rightInfo = process(X.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info2(maxDistance, height);
    }





//第三题(此题需要深度反复理解，视频（100:00 min）很全面)
/*
给定一颗二叉树的头节点，返回这颗二叉树中最大的二叉搜索子树的头节点（或节点个数）
搜索二叉树：整颗树上没有重复值，而且对于每颗子树，其左树节点值都小于头节点的值，右树节点值都大于头节点的值

对于以X为根节点的二叉树，求最大二叉搜索子树的节点个数
1）与X无关，那么要么是X左子树的最大二叉搜索子树的节点个数，要么是X右子树的最大二叉搜索子树的节点个数
2）与X有关，那么必须满足以下条件：X左子树为搜索二叉树、X右子树为二叉搜索树、左树最大节点小于X、右树最小节点大于X

需要左树信息：最大子搜size、是否搜索、max
需要右树信息：最大子搜size、是否搜索、min
此题与前两题不一样的一个关键点在于左右子树需要的信息不一样了；怎么解决？整合求全集即可！同时返回最大值和最小值
整合后需要返回信息：最大子搜size、是否搜索、max、min

 */

    public static int maxSubBSTSize2(Node head) {
        if(head == null) {
            return 0;
        }
        return process3(head).maxSubBSTSize;
    }

    public static class Info3 {
        public boolean isBST;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info3(boolean is, int size, int mi, int ma) {
            isBST = is;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }

    public static Info3 process3(Node X) {
        if(X == null){
            return null;
        }
        Info3 leftInfo = process3(X.left);
        Info3 rightInfo = process3(X.right);

        int min = X.value;
        int max = X.value;

        if(leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if(rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }

        int maxSubBSTSize = 0;
        if(leftInfo != null){
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }
        boolean isBST = false; //第一种可能结束


        //然后考虑第二种可能性，即与X有关时
        if(
                //左树和右树整体需要是BST
                ( leftInfo == null ? true : leftInfo.isBST)
                &&
                        (rightInfo == null ? true : rightInfo.isBST)
                &&
                //左树最大值<X,右树最小值大于X
                        (leftInfo == null?true:leftInfo.max<X.value)
                &&
                        (rightInfo == null?true:rightInfo.max<X.value)

               ) {
            //此时最大BST节点个数即为整颗以X为根节点的树的节点个数
            maxSubBSTSize = (leftInfo == null?0:leftInfo.maxSubBSTSize)+
                    (rightInfo ==null?0:rightInfo.maxSubBSTSize)
                    +
                    1;

            isBST = true;

        }


    }









//第四题
/*
派对的最大快乐值
员工信息的定义如下（就是一个多叉树）
class Employee{
    public int happy; //这名员工可以带来的快乐值
    List<Employee> subordinates; //这名员工有哪些直接下级
}
现在公司要办party，你可以决定哪些员工来哪些不来，规则如下：
1）如果某个员工来了，那么其所有直接下级都不能来；
2）派对的整体快乐值是所有员工快乐值的累加
3）你的目标是让派对的整体快乐值尽量大
给定一颗多叉树的头节点boss，返回派对的最大快乐值

思路：考虑以X为头结点的整棵树，分为X不来和X来两种情况
1)X不来：max（a来a的最大值，a不来最大值）+ max（b来b的最大值，b 不来最大值）+ max（c来c的最大值，c不来最大值）
2)X来（假设X有a、b、c三个孩子）:X自己的快乐值算上、a不来的情况下a子树的最大快乐值、b不来的情况下b子树的最大快乐值、
  c不来的情况下c子树的最大快乐值 = X整颗树的最大快乐值

 */

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static int maxHappy2 (Employee boss) {
        if(boss == null) {
            return 0;
        }
        Info4 all = process2(boss);
        return Math.max(all.yes, all.no);
    }

    public static class Info4 {
        public int yes;//头节点来的情况下，整棵树的最大快乐值
        public int no;//头节点不来的情况下，整棵树的最大快乐值

        public Info4(int y, int n){
            yes = y;
            no = n;
        }
    }

    public static  Info4 process2(Employee x) {
        if (x.nexts.isEmpty()) {
            return new Info4(x.happy, 0);
        }
        int yes = x.happy;
        int no = 0;
        for (Employee next : x.nexts) {
            Info4 nextInfo = process2(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info4(yes, no);

    }



















}








































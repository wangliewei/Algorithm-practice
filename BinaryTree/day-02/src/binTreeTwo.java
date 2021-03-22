//从一张纸条的下边向上边对折一次，展开后折痕向下；对折两次，折痕从上往下依次是下、下、上
//输入参数N，代表对折N次，从上到下打印折痕的所有方向
//例如，N=1时，打印down； N=2时，打印down down up

//二叉树 每个节点左孩子一定是下，右孩子一定是上 中序遍历
//并不是需要建出整颗树
//
public class binTreeTwo {
    public static void printAllFolds(int N){
        printProcess(1, N, true);
    }

    //递归过程，来到了某一个节点
    //i是节点的层数，N一共的层数，down==true 凹   down == false 凸
    public static void printProcess(int i, int N, boolean down){
        if(i > N){
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }

}

//O(N)

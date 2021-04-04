
/*
从暴力递归到动态规划1；
范围上的尝试模型
给一个整型数组，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌。
规定玩家A先拿，玩家B后拿，但是每次玩家只能拿两侧的牌，两个玩家都决定聪明。
请返回最后获胜者的分数（每个人拿到的牌数字和）

 */

public class puke {

    //方法一：两个函数，一个返回先手拿得到的最大分数，一个返回后手拿得到的最大分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int i, int j) {
        if(i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1， j), arr[j] + s(arr, i, j - 1));
    }

    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i + 1, j),f(arr, i, j - 1));//因为其对手也是绝顶聪明的，所以会让后手的人拿小的
    }




    //方法二：由递归改的动归
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //判断可变参数范围，构造二维表，因为是两个递归函数，所以需要两张二维表
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }

        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);

    }











}































/*
给定两个长度为N的数组weights和values，分别表示物品的重量和价值，背包载重为bag，不超过
bag的情况下，返回你能装下最多的价值是多少

 */


public class backpack {

    //方法一：递归解法,依然还是从左往右的尝试模型
    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process1(w, v, 0, 0, bag);
    }

    //alreadyw表示index之前的做了货物的选择，使你已经达到的重量是多少
    public static int process1(int[] w, int[] v, int index, int alreadyw, int bag) {

        if (alreadyw > bag) {//重量超了
            return -1;  //返回-1，没有方案，除此之外，表示返回的是一个真实的价值
        }

        //重量没超，但是没货了
        if(index == w.length) {
            return 0;
        }

        int p1 = process1(w,v,index + 1, alreadyw, bag);//没有要当前位置index的货物
        int p2next = process1(w, v, index+1, alreadyw+w[index], bag);//要当前货
        int p2 = -1;
        if(p2next != -1){
            p2 = v[index] + p2next;
        }
        return Math.max(p1, p2);//两个可能性，选择大的那个

    }




    //方法二：
    public static int maxValue(int[] w, int[] v, int bag) {
        return process2(w, v, 0, bag);
    }


    public static int process2(int[] w, int[] v, int index, int rest) {
        if(rest <= 0) {
            return 0;
        }

        if(index == w.length) {
            return 0;
        }

        int p1 = process2(w, v, index + 1, rest);
        int p2 = Integer.MIN_VALUE;
        if (rest >= w[index]){
            p2 = v[index] + process2(w, v, index + 1, rest - w[index]);
        }
        return Math.max(p1, p2);
    }


    //方法三: 从暴力递归到动态规划，首先看递归状态有没有重复（怎么看？试！），如有，则可能改成动态规划
    //最简单的做法就是傻缓存，号称记忆化搜索
    //如何改成一般的动归？以可变参数构造动归表，然后根据递归的规律来找表中的状态转移规律
    //以此题来说，可变参数就是index和rest
    public static int dpway(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N+1][bag+1];//java里默认初始化都是0
        for (int index = N - 1; index >= 0; index--) //第N行都是0
            for (int rest = 1; rest <= bag; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if(rest >= w[index]) {
                    dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index+1][rest])
                }

            }

    }


























}

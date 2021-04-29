/*
一个array，每个位置的数代表数值不同的硬币，每张数值的硬币都可以使用任意次，array中数值都是正数且无重复值
给定一个目标钱数aim，问有多少种方法可以凑够aim；

 */


import org.omg.CORBA.INTERNAL;

import java.util.HashMap;

public class multiBackpack {

    //定义一个函数int f(i, rest),表示从arr[i...]的i位置开始往后的所有部分，组成rest的方法有多少种
    public static int ways(int[] arr, int aim) {
        if(arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);

    }

    //此函数表示可以从arr的index位置往后的所有硬币任意次，返回组成rest的方法有多少种
    public static int process(int[] arr, int index, int rest) {
        if(rest < 0) {
            return 0;
        }
        if(index == arr.length) {// 没有货币可以选择了
            return rest == 0 ? 1 : 0 ; //如果正好rest=0，说明凑够，否则没救了，返回0吧
        }

        //当前有货币，从index位置开始
        int ways = 0;
        for (int zhang = 0; zhang * index <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang*arr[index]));
        }//这里可以保证rest不会小于0，所以可以删掉第一个base case

        return ways;

    }


    //下面开始改成记忆化搜索：分析可知递归过程有重复解      自顶向下的动态规划
    //记忆化搜索并不总是比动态规划效果好
    public static int ways2(int[] arr, int aim) {
        if(arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //由于可变参数有两个，所以需要想办法保存这个状态和该状态对应的解，
        //key："index_rest"  value：该状态对应的解
        //然后在递归过程中带着这个缓存去走一趟

        //为了简便一些，优化一下缓存结构，选择以下的二维数组来保存状态和该状态对应的解

        int[][] dp = new int[arr.length+1][aim+1];
        //一开始所有的过程都没有计算过，把所有位置的值都设为-1
        for (int i =0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(arr, 0, aim, dp);
    }

    //如果index和rest的参数组合，是没有算过的，那么一定有dp[i][j] = -1;
    //如果index和rest的参数组合，是算过的，那么一定有dp[i][j] > -1;
    public static int process2(int[] arr, int index, int rest, int[][] dp ) {

        if(dp[index][rest] != -1) { //已经算过，直接返回
            return dp[index][rest];
        }
        //下面就表示没算过，然后开始真的去算


//        if(rest < 0) {
//            return 0;
//        }
        //下面只要你要return了，那么在return之前先把其结果保存到缓存即该二维数组中。
        if(index == arr.length) {// 没有货币可以选择了
            dp[index][rest] = rest == 0 ? 1 : 0 ;
            return  dp[index][rest]; //如果正好rest=0，说明凑够，否则没救了，返回0吧
        }

        //当前有货币，从index位置开始
        int ways = 0;
        for (int zhang = 0; zhang * index <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang*arr[index]),dp);
        }//这里可以保证rest不会小于0，所以可以删掉第一个base case
        dp[index][rest] = ways;
        return ways;

    }





    //下面改成第一版的经典动态规划，要找到空间感，由于每个位置有枚举行为，所以此法和记忆化搜索方法等效
    //此题的枚举行为是可以优化的
    public static int ways3(int[] arr, int aim) {
            if(arr == null || arr.length == 0 || aim < 0) {
                return 0;
            }
            int N = arr.length;
            int[][] dp = new int[N+1][aim+1];

            //根据递归过程来改
            dp[N][0] = 1;//其余dp[N][1...aim] = 0
            //上面代码等于是填好了二维表的最后一行
            //对于任一位置，都依赖于其下面一行，
            //找到总体方向感，对此题来说，总体方向就是从下往上,由于每个位置都不依赖本行的值，所以无所谓从左往右还是从右往左
            for (int index = N - 1; index >= 0; index--) {
                for (int rest = 0; rest <= aim; rest++) {
                    int ways = 0;
                    for (int zhang = 0; zhang * index <= rest; zhang++) {
                        ways += dp[index + 1][rest - (zhang*arr[index])];
                    }
                    dp[index][rest] = ways;

                }
            }
        //

        return dp[0][aim];
    }



    //第二版动态规划：由于每一行是从左往右计算的，所以可以利用本行的之前计算过的值来优化枚举过程  视频三：124min
    //在ways3方法的基础上再次改进
    public static int ways4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;//其余dp[N][1...aim] = 0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] = dp[index][rest - arr[index]];
                }

            }
        }
        return dp[0][aim];

    }

}



/*
暴力 --》初步空间感，记忆化搜索 --》经典动态规划

 */








/*
FaceBook原题：
规定1和A对应，2和B对应，3和C对应；那么一个数字字符串比如“111”，就可以
转化为“AAA”、“KA”、“AK”
给定一个数字字符串str，返回有多少种转化结果
 */

//此题是从左往右的尝试模型1，一共有四种模型，四种模型覆盖95%的题

public class ArrayToString {

    public static int numres(String str) {
        if (str == null || str.length() == 0){
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process (char[] str, int i) {
        //终止位置，返回1种
        if (i == str.length) {
            return 1;
        }
        //0字符无法转化，直接返回0种方法
        if (str[i] == '0') {
            return 0;
        }

        //i没有到终止位置，并且i位置字符不是0
        if(str[i] == '1'){
            int res = process(str, i + 1)；//i自己作为单独的部分，后续还有多少种方法
            if(i + 1 < str.length) {
                res += process(str, i + 2);//i和i+1作为单独的部分，后续还有多少种方法
            }
            return res;
        }


        if(str[i] == '2') {
            int res = process(str, i + 1);
            if(i + 1 < str.length && (str[i + 1] >= '0' && str[i+1] <= '6')){
                res += process(str, i+2);
            }
            return res;
        }

        //如果i位置和i+1位置字符数字组大于26（或者i位置字符大于等于3），是无法将这两位拉出来作为一种分支的
        //此时只有一种选择，那就是将i位置拎出来，i+1继续递归去吧
        //str[i] == '3' ~ '9'
        return process(str, i + 1);
    }





    //动归方法：
    public static int dpway(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N+1];

        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if(str[i] == '0'){
                dp[i] = 0;
            }
            if(str[i] == '1'){
                dp[i] = dp[i+1];
                if(i+1 < str.length){
                    dp[i] += dp[i+2];
                }
            }
            if(str[i] == '2') {
                dp[i]= dp[i+1];
                if(i + 1 < str.length && (str[i + 1] >= '0' && str[i+1] <= '6')){
                    dp[i] = dp[i+2];
                }
            }

        }


        return dp[0];


    }






}

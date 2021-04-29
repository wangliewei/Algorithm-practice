
/*
如题：求最长公共子序列（不连续）



 */




public class t1{

    // 暴力递归方法
    public static int lcs(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        return process(str1, str2, N - 1, M - 1);
    }

    //递归含义：str1从0到i1 与 str2从0到i2的最长公共子序列长度是多少？
    public static int process(char[] str1, char[] str2, int i1, int i2) {
        if(i1 == 0 && i2 == 0){
            return str1[i1] == str2[i2] ? 1 : 0;
        }
        //下面表示i1和i2不同时为0
        if(i1 == 0) {
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1, i2-1) == 1) ? 1 : 0;
        }
        //i2 == 0时同理
        if(i2 == 0) {
            return ((str1[i1] == str2[i2]) || process(str1, str2, i1-1, i2) == 1) ? 1 : 0;
        }
        //下面表示i1和i2都不是0

        //case1：最长公共子序列的结尾既不是str1[i1]，也不是str2[i2]
        int p1 = process(str1, str2, i1 - 1, i2 - 1);

        //case2：最长公共子序列的结尾是str1[i1]，不是str2[i2]；所以要不要str2[i2]都一样
        int p2 =  process(str1, str2, i1, i2 - 1);

        //case3：最长公共子序列的结尾是str2[i2]，不是str1[i1]；所以要不要str1[i1]都一样
        int p3 = process(str1, str2, i1 - 1, i2);

        //case4：最长公共子序列的结尾既是str1[i1]，也是str2[i2]
        int p4 = -1;
        if(str1[i1] == str2[i2]){
            p4 = p1 + 1;
        }

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));

    }



    //改成动态规划
    public static int dp(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;

        int[][] dp = new int[N][M];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;

        //填第一行
        for (int i = 1; i < M; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }

        //填第一列
        for (int j = 1; j < N; j++) {
            dp[j][0] = str1[j] == str2[0] ? 1 : dp[j-1][0];
        }

        //填普遍位置
        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                //先让p2和p3比较一下;
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

                if(str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
                //比较结束，这里没有考虑可能性1，即p1，是因为p2和p3不可能比p1小


            }

        }

        return dp[N-1][M-1];

    }


    public static void main(String[] args) {
        String str1 = "A1BC23Z4";
        String str2 = "12O3YU4P";
        System.out.println(lcs(str1,str2));
        System.out.println(dp(str1,str2));
    }



}


































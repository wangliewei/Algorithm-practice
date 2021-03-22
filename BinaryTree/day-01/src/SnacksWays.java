//背包问题


public class SnacksWays {

    public static int ways1(int[] arr, int w) {
        return process(arr, 0. w);
    }


    //从左往右的经典模型
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {//没有容量了
             return -1;
        }

        if (index == arr.length) {//无零食可选
            return 1;
        }

        int next1 = process(arr, index + 1, rest);//不要当前位置的零食
        int next2 = process(arr, index + 1,rest - arr[index]);
        return next1 + (next2 == -1 ? 0 : next2 );

    }

    public static int ways3(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N][w+1];
        for (int i = 0; i < N; i++) {
            do[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j=1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j - arr[j]) >= 0 ? dp[i - 1][j - arr[i]])
            }
        }
        int ans = 0;
        for (int j = 0; j <= w; j++) {
            ans += dp[N-1][j];
        }
        return ans;
    }


}

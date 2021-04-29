//共有从1到N个位置，一个机器人一开始位于第M个位置，要求走K步，来到P位置；
//来到1位置时只能往右走，来到N位置时只能往左走，其余位置可往左可往右
//给定四个参数N、M、K、P，返回方法数


public class robotWalk {



    //方法一：暴力递归
    public static int way1 (int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        return walk1(N, M, K, P);
    }

    public static int walk1(int N, int cur, int rest, int P){
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            return walk1(N, 2, rest - 1, P);
        }
        if (cur == N) {
            return walk1(N, N - 1, rest - 1, P);
        }
        return walk1(N, cur + 1, rest - 1, P) + walk1(N, cur - 1, rest - 1, P);
    }




    //方法二：记忆化搜索 - 粗糙的动归
    public static int way2 (int N, int M, int K, int P) {
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        int[][] dp = new int[N+1][K+1];
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= K; j++){
                dp[i][j] = -1;
            }
        }
        return walk2(N, M, K, P,dp);
    }

    public static int walk2(int N, int cur, int rest, int P,int[][] dp){
        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) {
            dp[cur][rest] = walk2(N, 2, rest - 1, P);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walk2(N, N - 1, rest - 1, P);
            return dp[cur][rest];
        }
        dp[cur][rest] = walk2(N, cur + 1, rest - 1, P) + walk2(N, cur - 1, rest - 1, P);
        return dp[cur][rest];
    }








}

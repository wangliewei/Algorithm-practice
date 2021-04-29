

/*

给定一个整型矩阵，返回子矩阵（连续的一块长方形）的最大累加和

思路：以m*n的矩阵为例， 遍历只包含第1行、第1 2行、第1 2 3行、......、第1 2 ... m行
看哪种情形下和最大，单行求最大就是最大子数组问题，多行求最大需要先合并成一行（压缩数组），然后仍然是最大子数组问题
然后从第二行开始，重复上述过程

复杂度O(M^2 * N)   选择m和n中小的作为列数

没求一步，用max捕获一下，最终答案即为max

 */




public class SubMatrixMaxSum {

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;
        for (int i = 0; i != m.length; i++) { // 开始的行号i
            s = new int[m[0].length]; //
            for (int j = i; j != m.length; j++) { // 结束的行号j，i~j行是我讨论的范围
                cur = 0;
                for (int k = 0; k != s.length; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSum(matrix));

    }




}

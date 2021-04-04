/*
在N*N的棋盘上摆N个皇后，要求任何两个皇后在不同行、不同列、不同斜线上；
给定一个整数n，返回有多少种摆法；
n = 1; 返回1
n = 2或3； 返回0，因为怎么摆都不行
......
n = 8； 返回92

 */

public class NQueen {

    //方法一：暴力递归
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[];
        return process1(0, record, n);

    }


    //潜台词：record[0......i-1]的皇后，已经满足任两个不同行、列、斜线
    //目前来到了第i行
    //record[0......i-1]表示之前的行，放了的皇后的位置，例record[k] = 7，表示第k行的皇后放在了第7列
    //n代表一共有但是行，返回值为合理的摆法
    public static int process1(int i, int[] record, int n) {
        if (i == n) { //终止行
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) { //当前行在i行，尝试i行所有的列 ->j
            //当前i行的皇后放在j列，会不会和之前的（0...i-1）的皇后，不同行、列、斜线
            //如果是，认为有效
            //如果不是，认为无效
            if(isValid(record, i, j)) {
                record[i] = j;
                res += process1(i+1, record, n);
            }
        }
        return res;

    }

    //record[0...i-1]你需要看，record[i....]不需要看
    //返回i行皇后，放在了j列是否有效
    public static boolean isValid(int[] record, int i, int j) {
        for(int k = 0; k < i; k++){ //之前的某个k行的皇后
            if(j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) { //两个条件，一个共列、一个共斜线
                return false;
            }
        }
        return true;
    }









    //用位运算加速，但是复杂度跟上面一样，只是加速常数项
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if(colLim == limit) {
            return 1;
        }
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim|mostRightOne) <<1,
                    (rightDiaLim|mostRightOne) >>> 1);
        }
        return res;
    }

















}



























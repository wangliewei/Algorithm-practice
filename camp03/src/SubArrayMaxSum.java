

/*
给定一个数组arr，返回子数组的最大累加和

思路：两个变量 cur记录当前值   max记录最大值（初始化为系统最小）
cur每次加进来一个数，都要看一下是否比max大，如果是，把max更新成cur，如果cur小于零，则把cur更新成零，此时不用管max

即使arr中全是负数或者全是非整数或者正负数都有 ，依然能找到正确解

证明：max一定可以捕获最大且最长的那组解

假设答案法

为什么cur小于0时要更新成0？ 因为你答案里的任何前缀都是不会小于零的

 */

public class SubArrayMaxSum {

    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
        System.out.println(maxSum(arr1));

        int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
        System.out.println(maxSum(arr2));

        int[] arr3 = { -2, -3, -5, -1 };
        System.out.println(maxSum(arr3));

    }

}

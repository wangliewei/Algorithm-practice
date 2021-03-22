
//找到第k小的数值对
//输入是一个无序数组。例如{2,1,3}
//其产生的所有数值对包括（1,1）、（1,2）、（1,3）、（2,1）、（2,2）、（2,3）、（3,1）、（3,2）、（3,3）
//按照第一维数字排序，如果第一维数字相同，再按照第二维数字排序

import java.util.*;

//java.util的包下面，有个Array类，这个类有个sort方法。。。。
public class kthMinPair {

    //kthMinPair1  暴力解


    //kthMinPair2  复杂度：O( N * log N )
    public static int[] kthMinPair2(int[] arr, int k){
        int N = arr.length;
        if(k > N * N) {
            return null;
        }


        Arrays.sort(arr);//复杂度：O( N * log N )

        //第k小的数值对，先找到它的第一维数字，即firstNum
        int firstNum = arr[(k-1) / N];

        int lessFirstNumSize = 0;//找出比firstNum小的数字有多少个

        int firstNumSize = 0;//找出等于firstNum的数有多少个
        //   <=firstNum
        for (int i =0; i < N && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            } else{
                firstNumSize++;
            }
        }

        //排除前面数作为第一维的数值对，因为它们肯定比目标数值对小，
        //然后在确定的第一维数字产生的所有数值对中找到第rest小的就是目标数值对
        int rest = k - (lessFirstNumSize * N);
        return new int[] { firstNum,arr[(rest - 1) / firstNumSize] };
    }


    //kthMinPair3  复杂度：O( N )
    public static int[] kthMinPair3(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }

        //在无序数组中，找到第k小的数
        int firstNum = getMinKthByBFPRT(arr, ((k - 1) / N) + 1);
        int lessFirstNumSize = 0;
        int firstNumSize = 0;
        for (int i = 0; i < N ; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            }
            if (arr[i] == firstNum) {
                firstNumSize++;
            }
        }

        int rest = k - (lessFirstNumSize * N);
        return new int[] {firstNum, getMinKthByBFPRT(arr, ((rest - 1) / firstNumSize))};

    }

    public static int getMinKthByBFPRT(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k - 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2,2,2,3,3,3,4,4,5,5,7};
        int[] res = kthMinPair3(arr,35);
        System.out.println(res[0]+res[1]);
    }


}



//两种面试题：业务题、技巧题
//主技巧的题：背后有一个基础的数据结构或者算法原型，可推广
//主业务的题：最优解就是在该题专有的业务上，找到聪明的做法
//笔试中，业务题占60%； 面试中，技巧题占75%
//刷题的时候主要刷技巧题
//如何在lc上找主技巧的题，看顶比踩多的

















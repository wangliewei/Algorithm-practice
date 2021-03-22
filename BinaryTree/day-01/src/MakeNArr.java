
//生成长度为size的达标数组
//达标：对于任意的i<k<j，满足[i] + [j] != 2*[k]

import java.lang.*;

public class MakeNArr {
    public static int[] makeNArr(int size){
        if(size == 1){
            return new int[] { 1 };
        }
        //size
        //一半长达标来构造
        //下面这一步是要向上取整，如果size是奇数，例如size=7，那么halfSize=4，
        //4个奇数 + 3个偶数
        int halfSize = (size + 1) / 2;
        int[] base = makeNArr(halfSize);
        //base-->生成等长奇数达标的
        //base-->生成等长偶数达标的
        int[] ans = new int[size];
        int index = 0;
        for(; index < halfSize;index++){
            ans[index] = base[index] * 2 + 1;
        }
        for(int i =0;index < size;index++,i++){
            ans[index] = base[i] * 2;
        }
        return ans;
    }

    //下面这个方法是用来检验ans是否满足条件，即是否达标
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for(int i = 0; i < N; i++) {
            for(int k = i+1; k<N; k++) {
                for(int j = k+1; j < N; j++) {
                    if(arr[i] + arr[j] == arr[k]*2){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
//        for (int N = 1; N < 1000; N++) {
//            int[] arr = makeNArr(N);
//            if(!isValid(arr)) {
//                System.out.println("Oops!");
//            }
//        }
        System.out.println(isValid(makeNArr(10)));

        System.out.println("test end");

    }









}


//例如abcdef,要求循环左移4个长度，结果是efabcd,要求时间复杂度O(N)，空间复杂度O(1)，众所周知的方法是三次逆序
//下面是一种其它方法见https://www.bilibili.com/video/BV1t5411N7NH?p=5&spm_id_from=pageDriver  p5



public class RotateString {
    public static String rotate1(String s, int leftSize) {
        if (leftSize <= 0 && leftSize >= s.length()) {
            return s;
        }
        return process1(s.toCharArray(), 0, leftSize - 1, s.length() - 1);
    }

    //在str上，如果认为str[L...M]是左部分，str[M+1...R是右部分]
    //请原地调整，只能用有限几个变量，左右两部分互换
    public static String process1(char[] str, int L, int M, int R) {
        reverse(str, L, M);
        reverse(str, M+1, R);
        reverse(str, L, R);
        return String.valueOf(str);
    }

    public static void reverse(char[] str, int L, int R) {
        while(L < R) {
            char tmp = str[L];
            str[L++] = str[R];
            str[R--] = tmp;
        }
    }

    //这个方法比较复杂，找个实例理解，或者看左神
    //此算法最坏复杂度跟上面那个一样，但是其优势在于不是每次都达到最坏复杂度，一般情况下交换次数比上面的算法少
    //其意义在于当交换两个字符的代价很大时，能够节省交换代价
    public static String rotate2(String s, int leftSize) {
        if (leftSize <= 0 && leftSize >= s.length()) {
            return s;
        }
        char[] str = s.toCharArray();
        int L = 0;
        int R = str.length - 1;
        int lpart = leftSize;
        int rpart = str.length - leftSize;
        int same = Math.min(lpart, rpart);
        int diff = lpart - rpart;
        exchange(str, L, R, same);
        while (diff != 0) {

            //diff>0,说明交换之前左侧比右侧大，那么小的交换之后该部分永远不动，即其最终位置，所以L要加上same
            if (diff > 0) {
                L += same;
                lpart = diff;
            } else{
                R -= same;
                rpart = -diff;
            }
            same = Math.min(lpart,rpart);
            diff = lpart - rpart;
            exchange(str, L, R, same);

        }
        return String.valueOf(str);

    }


    //这个跟reverse不一样，这个函数是str从start位置开始往右数size大小个，和str从end往左数size大小个 进行交换
    public static void exchange(char[] chas, int start, int end, int size) {
        int i = end - size + 1;
        char tmp = 0;
        while (size-- != 0) {
            tmp = chas[start];
            chas[start] = chas[i];
            chas[i] = tmp;
            start++;
            i++;
        }
    }





}

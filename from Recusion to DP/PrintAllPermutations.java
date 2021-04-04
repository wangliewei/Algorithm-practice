import java.util.ArrayList;

//打印一个字符串的全排列
public class PrintAllPermutations {

    public static ArrayList<String> permutation(String str) {

        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();

    }

    //str[0...i-1]都是已经做好决定的
    //str[i...]都有机会来到i位置
    //i终止位置, str当前的样子就是一种结果，放到res中去
    public static void process(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }

        //如果i没有终止，那么其后面的字符都可以来到i位置
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            process(str, i + 1； res);
            swap(str, i, j);
        }

    }






    //字符串全排列，无重复； 分支限界
    public static void process2(char[] str, int i, ArrayList<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }

        boolean[] visit = new boolean[26];

        for (int j = i; j < str.length; j++) {

            if(!visit[str[j]] - 'a') {//此条件表明这种字符没出现过
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process2(str, i + 1； res);
                swap(str, i, j);
            }
        }

    }





    public static char[] swap(char[] str, int i, int j) {
        char tmp;
        tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
        return str;
    }



}

/*
warning：String类型和char[]类型的length方法的区别
String类型的length方法有括号
数组有length属性，没有length()方法；String只有length()方法，没有length属性
 */
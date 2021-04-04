
/*
任何动态规划的问题都是某暴力尝试过程的优化
子串必须连续， 子序列可以不连续但是要求相对次序不能乱

 */

//中序无法反序列化，可以找一个简单的反例

import java.util.ArrayList;
import java.util.List;



public class PrintAllSubsquences {

    //打印一个字符串的全部子序列
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;

    }

    //str固定不变； index来到的位置，要或者不要
    //如果index来到了str中的终止位置，把沿途路径所形成的答案放入ans中
    //之前作出的选择就是path
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if(index == str.length) {
            ans.add(path);
            return;
        }
        String no = path;
        process1(str, index + 1, ans, no);
        String yes = path + String.valueOf((str[index]));
        process1(str, index+1, ans, yes);

    }



    //打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    //思路一：先像上面的方法一样，把其中List换成HashSet（自动去重）









}































/*
给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to，list中没有
重复字符，所有的字符串都是小写的。
规定:start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成的新字符串必须在list中存在。
请返回所有最短的变换路径。
【举例】
start="abc", end="cab" ,list={cab,acc,cbc,ccc,cac,cbb,aab,abb}
转换路径的方法有很多种，但是最短的是以下几种
abc-abb-aab-cab
abc-abb-cbb-cab
abc-cbc-cac-cab
abc-cbc-cbb-cab

思路：
宽搜+深搜
先把list中的字符串构建成一个图，节点就是每个字符串，如果两个字符串的距离是1，那么
把它俩连一条边，我们的目的是生成一张表，key是每个字符串，v是其对应的邻居   复杂度：O(N^2 * K)    N是list中字符串个数  K是每个字符串的长度
上面的过程可以优化，用HashSet，把每个字符串有可能的邻居全列出来，然后去HashSet里查 复杂度：O(K^2)

根据实际情况比较O(N^2 * K)和 O(K^2)那个更小，选择哪种方法


 */


import java.util.*;

public class WordMinPaths {

    public static List<List<String>> findMinPaths(
            String start,
            String end,
            List<String> list) {
        list.add(start); //先把start加到list中
        HashMap<String, ArrayList<String>> nexts = getNexts(list); //生成邻居表
        HashMap<String, Integer> distances = getDistances(start, nexts);




        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, end, nexts, distances, pathList, res);
        return res;
    }

    public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        Set<String> dict = new HashSet<>(words); // List 所有东西放入 set
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }

    private static ArrayList<String> getNext(String word, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        return res;
    }

    public static HashMap<String, Integer> getDistances(String start,
                                                        HashMap<String, ArrayList<String>> nexts) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    distances.put(next, distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    // 现在来到了什么：cur
    // 目的地：end
    // 邻居表：nexts
    // 最短距离表：distances
    // 沿途走过的路径：path上{....}
    // 答案往res里放，收集所有的最短路径
    private static void getShortestPaths(
            String cur, String to,
            HashMap<String, ArrayList<String>> nexts,
            HashMap<String, Integer> distances,
            LinkedList<String> path,
            List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<String>(path));
        } else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();
    }

    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }







}

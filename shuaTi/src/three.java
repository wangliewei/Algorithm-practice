
/*
给定一个由字符串组成的数组String[] str, 给定一个正数k，
返回词频最大的前k个字符串，假设结果是唯一的。

暴力思路：哈希 + 大根堆或小根堆（top K问题）

四：100min

 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class three {

    public static class Node {
        public String str;
        public int times;

        public Node (String s, int t){
            str = s;
            times = t;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }

    }

    public static void printTopKAndRank(String[] arr, int topK) {
        if(arr == null || arr.length == 0 || topK < 1) {
            return ;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            if(!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        topK = Math.min(arr.length, topK);
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        //维护这个堆
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node cur = new Node(entry.getKey(), entry.getValue());
            if(heap.size() < topK) {
                heap.add(cur);
            } else {
                if(heap.peek().times < cur.times) {
                    heap.poll();
                    heap.add(cur);
                }
            }
        }
        while (!heap.isEmpty()) {
            System.out.println(heap.poll().str);
        }

    }




}


















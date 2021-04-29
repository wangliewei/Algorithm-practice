
/*
请实现如下结构：
TopRecord{
    public TopRecord(int K); //构造时事先指定好K的大小，构造后就固定不变了
    public void add(String str); // 向该结构中加入一个字符串，可以重复加入
    public List<String> top; // 返回之前加入的所有字符串中，词频最大的k个

}

要求：
add方法，复杂度O(log K)
top方法， 复杂度O(K)

115min
思路:需要三个数据结构：一个统计词频的表、一个记录每个词频对应堆中的位置表、一个小根堆



 */


import java.util.HashMap;

public class four {

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopKRecord {
        private Node[] heap; //堆，因为是完全二叉树，所以直接用数组就可以实现
        private int heapSize;
        // string -> Node(times)
        private HashMap<String, Node> strNodeMap; //词频表
        private HashMap<Node, Integer> nodeIndexMap;//记录位置的表

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }


        //str用户现在给我的
        public void add(String str) {
            Node curNode = null;
            int preIndex = -1;// str之前在堆上的位置
            //查看词频，看看有没有关于这个str的记录
            if(!strNodeMap.containsKey(str)) { //之前没有出现过
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode); //加入词频表
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            //词频表修改完毕

            if(preIndex == -1) { //不在堆上
                if(heapSize == heap.length) { //堆满了
                    if(heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, heapSize);

                    }
                } else {//堆没满
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            } else { //str已经在堆上了
                heapify(preIndex, heapSize)；
            }

        }



        public void printTopK(Node[] heap) {
            System.out.println("TOP： ");
            for (int i = 0; i != heap.length; i++){
                if(heap[i] == null) {
                    break;
                }
                System.out.println("Str: " + heap[i].str);
                System.out.println("Times: " + heap[i].times);
            }

        }


        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if(heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index *2 + 1;
            int r = index * 2 +2;
            int smallest = index;
            while(l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = 1;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if(smallest != index ) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;

            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }





    }















}


























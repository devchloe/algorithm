package com.company.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252 {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;
    static int[]indegree;
    static LinkedList<Integer>[] graph;
    static Queue<Integer> searchQ = new LinkedList<Integer>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        graph = new LinkedList[N+1];

        for (int i=1; i<=N; i++) {
            indegree[i] = 0;
            graph[i] = new LinkedList<>();
        }

        int from, to;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            indegree[to]++;
        }
        sort();

    }

    private static void sort() {
        for (int i=1; i<=N; i++) {
            if (indegree[i] == 0) searchQ.offer(i);
        }

        for (int i=1; i<=N; i++) {
            // while (!searchQ.isEmpty())
            int zeroNode = searchQ.poll();
            System.out.print(zeroNode + " ");
            for (int linkNode : graph[zeroNode]) {
                indegree[linkNode]--;

                if (indegree[linkNode] == 0) {
                    searchQ.offer(linkNode);
                }
            }
        }
    }
}

package com.company.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1922 {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;

    static int[][] edges;
    static int[] disjointSets;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        disjointSets = new int[N+1];
        for (int i=1; i<=N; i++) {
            disjointSets[i] = i;
        }

        edges = new int[M][3];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i][0] = from;
            edges[i][1] = to;
            edges[i][2] = w;
        }

        edgesFeeAscendingSort();

        int fee = 0;
        for (int[] e: edges) { // 비용 오름차순으로 정렬된 edges
            if (isUnion(e[0], e[1])) continue; // 비용이 적은 쪽을 택해서 이미 parent끼리 연결되어 있는 경우, 직접 연결하기는 비싸지만 연결연결되어서 연결할 수 있는 케이스도 있음
            // union-find를 통해 집합을 만들어주니까 가능
            union(e[0], e[1]);
            fee += e[2];
        }

        System.out.println(fee);
    }

    private static boolean isUnion(int from, int to) {
        return find(disjointSets[from]) == find(disjointSets[to]);
    }

    private static void edgesFeeAscendingSort() {
        Arrays.sort(edges, new Comparator<int[]>() { // 오름차순 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] < o2[2]) return -1;
                if (o1[2] > o2[2]) return 1;
                return 0;
            }
        });
    }

    private static void union(int from, int to) {
        int fromRoot = find(disjointSets[from]);
        int toRoot = find(disjointSets[to]);
        disjointSets[fromRoot] = toRoot;
    }

    private static int find(int x) {
        if (disjointSets[x] == x) return x;
        disjointSets[x] = find(disjointSets[x]);
        return disjointSets[x];
    }
}

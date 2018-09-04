package com.company.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2606 {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;
    static int[] parent;
    static final int ROOT = 1;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];

        for (int i=1; i<=N;i++) {
            parent[i] = i;
        }

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int count = 0;
        for (int i=1; i<=N; i++) {
            if (parent[i] == 1) count++;
        }
        System.out.println(count);
    }

    private static void union(int a, int b) {
//        int aRoot = find(a);
//        int bRoot = find(b);
//        parent[aRoot] = bRoot;
        int x = find(b);
        if (x == 1) {
            parent[find(a)] = x;
        } else {
            parent[find(b)] = x;
        }
    }

    private static int find(int b) {
        if (parent[b] == b) return b;
        int root = find(parent[b]);
        parent[b] = root;
        return parent[b];
    }

    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
}

package com.company.graph;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int M;

    static int[][] distance;
    static List<Integer>[] nodes;
    static int[][] parents;
    static int[] depth;
    static int K;

    static int fee1, fee2;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        nodes = new List[N+1];
        depth = new int[N+1];
        for (int i=1; i<=N; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        int n = N;
        while (n > 0) {
            n >>= 1;
            K++;
        }
        parents = new int[K][N+1];

        distance = new int[N+1][N+1];
        for (int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            distance[a1][a2] = w;
            distance[a2][a1] = w;

            nodes[a1].add(a2);
            nodes[a2].add(a1);
        }

        parents[0][1] = 0;
        bfs();

        M = Integer.parseInt(br.readLine());

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lca(d, e);
            bw.write(Math.min(fee1, fee2) + " " + Math.max(fee1, fee2) + "\n");
        }


        br.close();
        bw.flush();
        bw.close();
    }

    private static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int d = depth[a] - depth[b];
        int k = 0;
        fee1 = 0;
        fee2 = 0;

        while (d>0) {
            if (d%2 == 1) a = parents[k][a];
            d >>= 1;
            fee1 += distance[a][parents[k][a]];
            k++;
        }

        if (a == b) return a;

        for (k=K-1; k>=0; k--) {
            if (parents[k][a] != parents[k][b]) {
                a = parents[k][a];
                b = parents[k][b];
                fee1 += distance[a][parents[k][a]];
                fee2 += distance[b][parents[k][b]];
            }
        }

        return parents[0][a];
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(1);

        int v;
        while (!q.isEmpty()) {
            v = q.poll();
            for (int n : nodes[v]) {
                if (n == parents[0][v]) continue;
                parents[0][n] = v;
                depth[n] = depth[v] + 1;
                q.add(n);
            }
        }
    }
}
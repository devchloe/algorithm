package com.company.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 서로소 집합 표현 */
public class Main_MyFirstWrong1717 {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer>[] G = new ArrayList[N+1];

        for (int i=1; i<=N; i++) {
            G[i] = new ArrayList<>();
        }
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                G[a].add(b);
                G[a].addAll(G[b]);
                G[b].add(a);
                G[b].addAll(G[a]);
            } else {
                System.out.println(G[a].contains(b) ? "YES" : "NO");
            }
        }
    }
}

/**
 7 9
 0 1 3
 1 1 7
 0 7 6
 1 7 1
 0 3 7
 0 4 2
 0 1 1
 1 3 6
 1 1 3
 * **/
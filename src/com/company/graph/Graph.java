package com.company.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Graph {
    /**
     그래프 표현

     간선리스트 표현
     - a[간선][시작/종료노드/가중치]

     인접행렬 - 간선 여부 체크
     - a[시작][종료] = 1/0/가중치
     - 인접여부 확인

     인접리스트
     - a[시작] = new int[]{종료1, 종료2, ...} -> List or Node[]
     * */
    static int N;
    static int M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] G = new int[M][2];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            G[i][0] = s;
            G[i][1] = e;
        }

        for (int i=0; i<M; i++) {
            System.out.println(Arrays.toString(G[i]));
        }


//        List<Integer>[] G2 = new List[N+1];
        List<int[]>[] G2 = new List[N+1];
        for (int i=1; i<=N; i++) {
            G2[i] = new ArrayList<int[]>();
        }
    }
}

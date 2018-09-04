package com.company.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717 {
    static int N;
    static int M;
    static BufferedReader br;
    static StringTokenizer st;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];

        for (int i=1; i<=N;i++) {
            parent[i] = i;
        }

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(a, b);
            } else {
                System.out.println(isUnion(a, b)? "YES" : "NO");
            }
        }

    }

    private static void union(int a, int b) {
//        int aRoot = find(a);
//        int bRoot = find(b);
//        parent[aRoot] = bRoot;
        parent[find(a)] = find(b); // b의 parent node 끝(root)을 찾아서 a의 root와 연결
    }

    private static int find(int b) {
        if (parent[b] == b) return b; // 종료조건, 최상위 root는 자기 자신을 parent로 가짐
        int root = find(parent[b]);
        parent[b] = root; // 시간을 단축시키기 위해서 찾은 최상위 root로 값을 변경시켜준다 (direct로 root 참조하는 것처럼)
        // 1  3   6  7
        // 3  3   7  3  => 여기서 6의 parent 7을 통해 집합 주인인 3을 찾음, parent를 7로 두지 않고 3으로 변경, isUnion(1,6)할 때 한번 연산으로 비교할 수 있게
        return parent[b];
    }

    private static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }
}

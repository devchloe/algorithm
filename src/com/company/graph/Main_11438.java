package com.company.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main_11438 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M;
    static List<Integer>[] nodes; // 인접리스트 구성

    // bfs, dfs를 이용해서 각 노드의 부모노드와 깊이를 구성한다
    static int ROOT = 1;
    static int[][] parents;
    static int[] depth;
    static int K = 0;

    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        //System.setIn(new FileInputStream(Main.class.getResource("sample_input.txt").getPath()));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nodes = new List[N + 1]; // 간선이 몇개인지 알 수 없으므로 간선정보 배열을 사용할 수 없음, 노드별 인접노드를 저장
        depth = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<Integer>();
        }

        int n = N;
        while (n > 0) {
            n >>= 1;
            K++; // 트리의 높이, 노드수를 2로 계속 나누어서 0이 될때까지 하면 높이가 나옴, 전체 노드수 <= 2^(k+1), (k+1)=높이
        }
        parents = new int[K][N+1];

        int a,b;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }

        parents[0][1] = 0; // 루트의 바로 위(2^0번째) 부모는 0으로 설정
        bfs();


        // 이제 서로 다른 두 노드의 depth를 맞추는 반복 연산을 줄이기 위해 2^1번째, 2^2번째, 2^3번째 부모를 저장해둠
        for (int k=1; k<K; k++) {
            for (int node=1; node<=N; node++) {
                parents[k][node] = parents[k-1][parents[k-1][node]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) { // while(M-- > 0)
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(lca(a, b)+"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static int lca(int a, int b) {

        if (depth[a] < depth[b]) { // 더 depth가 깊은 b를 a로 바꾼다
            int temp = a;
            a = b;
            b = temp;
        }

        int d = depth[a] - depth[b]; // depth가 깊은 것을 골라서 끌어올린다
        int k = 0;
        while (d>0) { // b와 가장 가깝게 한 번에 끌어 올린다, depth가 같은 경우는 자연스럽게 제외됨, 남은 케이스는 다른 조상 밑에 있거나 부모와 조상 노드가 주어진 경우임
            if (d%2 == 1) a = parents[k][a];
            d >>= 1;
            k++;
        }

        if (a == b) return a; // 끌어올린 a가 b와 같은 경우

        for (k=K-1; k>=0; k--) { // 같은 depth를 만들었으니 이제 하나씩 올라가면서 부모가 같은지를 비교
            if (parents[k][a] != parents[k][b]) {
                a = parents[k][a];
                b = parents[k][b];
            }
        }

        return parents[0][a];
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(ROOT); // 루트 노드부터 시작

        int v;
        while(!queue.isEmpty()) {
            v = queue.poll();
            for (int n : nodes[v]) {
                if (n == parents[0][v]) continue; // 양방향으로 인접리스트를 구성했기 때문에 부모, 자식 양쪽에 서로를 참조하고 있음, 따라서 이미 부모로 설정되었으면 다음 노드로 넘어감
                parents[0][n] = v; // 각 노드의 바로위 2^0번째 부모를 저장
                depth[n] = depth[v] + 1;
                queue.add(n);
            }
        }
    }
}

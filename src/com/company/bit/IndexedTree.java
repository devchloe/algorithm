package com.company.bit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/** 구간합 */
class IndexedTree {
    int bIdx, size;
    long tree[];

    public IndexedTree(int size) {
        super();
        this.size = size;

        int len = 1;
        while (len < this.size) { // leaf node 개수와 가장 가까운 2^k 개 노드개수를 구함, N=5인 경우 len=8
            len <<= 1;
        }
        len <<= 1; // 전체 노드 개수는 2^(k+1)
        tree = new long[len]; // 노드가 2^(k+1)인 트리 생성

        this.bIdx = len/2-1; // bIdx=leaf node 시작 전 마지막 인덱스, leaf node의 첫번째 인덱스(bIdx)는 깊이가 k인 층의 노드 개수와 같음 (2^k), root node 인덱스는 1부터 시작한다고 가정
    }

    public void setData(long[] data){
        for (int i = 1; i <= size; i++) { // 주어진 leaf node input값 셋팅
            tree[bIdx+i] = data[i];
        }

        for (int i = bIdx; i >=1; i--) { // leaf node로부터 구간합 구해서 parent node 값 구하기
            tree[i] = tree[2*i] + tree[2*i+1];
        }
    }

    public void update(int idx, long value){
        int tIdx = bIdx + idx;
        tree[tIdx] = value;

        while((tIdx>>=1) > 0){ // parent node가 root node를 넘어서지 않을 때까지 2로 나누면서 parent 값(구간합)을 재계산
            tree[tIdx] = tree[tIdx*2]+tree[tIdx*2+1];
        }
    }

    public long query(int s, int e){
        long sum = 0;
        s += bIdx; e += bIdx;

        while (s <= e) { // 시작 노드와 종료 노드 구간합을 구하기 위해 parent로 올라오다가 parent에서 만나거나 교차하면 종료
            if(s%2 == 1) sum += tree[s++];
            if(e%2 == 0) sum += tree[e--];
            s >>= 1;
            e >>= 1;
        }

        return sum;
    }

    public String toString() {
        return "IndexTree [size=" + size + ", tree=" + Arrays.toString(tree) + "]";
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static long[] arr;
    static int N;
    static int M;
    static int K;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        IndexedTree it = new IndexedTree(N);
        it.setData(arr);

        int a, b, c;
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if(a == 1){
                it.update(b, c);
            }else{
                bw.write(it.query(b, c)+"\n");
            }
        }

        long end = System.currentTimeMillis();
        br.close();
        bw.flush();
        bw.close();
    }
}

/**
 5 2 2
 1
 2
 3
 4
 5
 1 3 6
 2 2 5
 1 5 2
 2 3 5

 17
 12
 * */
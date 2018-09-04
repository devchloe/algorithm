package com.company.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/** 트리선회 */
public class Main {

    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[26][2];

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = st.nextToken().charAt(0) - 'A';
            char y = st.nextToken().charAt(0);
            char z = st.nextToken().charAt(0);

            if (y == '.') {
                arr[x][0] = -1;
            } else {
                arr[x][0] = y - 'A';
            }

            if (z == '.') {
                arr[x][1] = -1;
            } else {
                arr[x][1] = z - 'A';
            }
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);

    }

    private static void postOrder(int idx) {
        if (idx == -1) return;
        postOrder(arr[idx][0]);
        postOrder(arr[idx][1]);
        System.out.print((char)(idx + 'A'));
    }

    private static void inOrder(int idx) {
        if (idx == -1) return;
        inOrder(arr[idx][0]);
        System.out.print((char)(idx + 'A'));
        inOrder(arr[idx][1]);
    }

    private static void preOrder(int idx) {
        if (idx == -1) return;
        System.out.print((char)(idx + 'A'));
        preOrder(arr[idx][0]);
        preOrder(arr[idx][1]);
    }

    /**
     7
     A B C
     B D .
     C E F
     E . .
     F . G
     D . .
     G . .

     ABDCEFG
     DBAECFG
     DBEGFCA
     **/
}

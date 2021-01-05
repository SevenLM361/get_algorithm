package com.rad.algorithm;

/**
 * 斐波那契数列
 * https://leetcode-cn.com/problems/fibonacci-number/
 * @author: rad
 * @date: 2020/12/30
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        System.out.println(fib(10));
        System.out.println("==================");
        System.out.println(fibByMemo(10));
        System.out.println("==================");
        System.out.println(fibByDpTable(10));
        System.out.println("==================");
        System.out.println(fibByStatusTransfer(10));
    }

    /**
     * 暴力递归
     * 时间复杂度：O(2^n)
     */
    public static int fib(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * 带备忘录的递归算法->解决【重叠子问题】
     * 时间复杂度：子问题个数为 O(n) * 解决一个子问题需要的时间为 O(1) = O(n)
     */
    public static int fibByMemo(int N) {
        if (N < 1) {
            return 0;
        }
        // 备忘录
        int[] memo = new int[N + 1];
        return memoHelper(memo, N);
    }

    private static int memoHelper(int[] memo, int n) {
        // base case
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        int n1 = memoHelper(memo, n - 1);
        int n2 = memoHelper(memo, n - 2);
        memo[n] = n1 + n2;
        return memo[n];
    }

    /**
     * dp数组迭代法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int fibByDpTable(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 状态转移方程：f(n) = {1, n=1,2 | f(n-1) + f(n-2), n>2}
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 思路：把dp table压缩为 -> 两个整型保存
     */
    public static int fibByStatusTransfer(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}

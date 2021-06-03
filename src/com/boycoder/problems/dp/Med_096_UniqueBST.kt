package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc:
 */
/**
Ideal
    For an array, we pick a position to generate the BST, the left side become left child, right side become right child.
    For the child, it can split until it become 1

 */
object Med_096_UniqueBST {
    private var cache = intArrayOf()
    fun numTrees(n: Int): Int {
        // cache = IntArray(n + 1)
        return num(n)
    }

    // dp
    private fun num(n: Int): Int {
        val dp = IntArray(n + 1)

        dp[0] = 1
        dp[1] = 1

        for (i in 2..n) {
            for (j in 0 until i) {
                dp[i] = dp[i] + dp[j] * dp[i - j - 1]
            }
        }

        return dp[n]
    }

    // recursion + cache
    private fun num2(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return 1
        if (n == 2) return 2

        if (cache[n] != 0) {
            return cache[n]
        }

        var count = 0
        for (i in 0 until n) {
            count = count + num2(i) * num2(n - i - 1)
        }

        cache[n] = count

        return count
    }

    // recursion
    private fun num1(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return 1
        if (n == 2) return 2

        var count = 0
        for (i in 0 until n) {
            count = count + num1(i) * num1(n - i - 1)
        }

        return count
    }
}

fun main() {
    val res = Med_096_UniqueBST.numTrees(3)
    asserts(res, 5)
}
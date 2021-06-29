package com.boycoder.problems.dp

import com.boycoder.utils.WrongResultException
import com.boycoder.utils.asserts

/**
 For the path(m, n), it comes from path(m - 1, n) and path(m, n - 1)
 1. recursion
 2. recursion + cache + state compress
 3. dp
 */
object Easy_062_UniquePath {
    var cache: IntArray = intArrayOf()
    val BASE = 101

    fun uniquePaths(m: Int, n: Int): Int {
        return path3(m, n)
    }

    // dp
    private fun path3(m: Int, n: Int): Int {
        // dp with init
        val dp: Array<IntArray> = Array(m + 1){ IntArray(n + 1){1} }

        for (i in 2..m) {
            for (j in 2..n) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
            }
        }

        return dp[m][n]
    }

    // out of time
    private fun path1(m: Int, n: Int): Int {
        if (m == 1 || n == 1) return 1

        return path1(m, n - 1) + path1(m - 1, n)
    }

    // recursion + cache + state compress
    private fun path2(state: Int): Int {
        val m = state / BASE
        val n = state % BASE

        if (m == 1 || n == 1) return 1

        if (cache[state] != 0) {
            return cache[state]
        }

        val res = path2(m * BASE + n - 1) + path2((m - 1) * BASE + n)
        cache[state] = res

        return res
    }
}
fun main() {
    val res = Easy_062_UniquePath.uniquePaths(3, 7)
    asserts(res, 28)
    println(res)
}
package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc:
 */
/**
6       7        8         9
1, 5    1,6      1,7       1，8
3, 3    2,5      2,6       2，7
        3,4      3,5       3，6
                           4，5

break(i) = (2 * max(break(i - 2), i - 2), 3 * max(break(i - 3), i - 3)
 */
object Med_343_IntegerBreak {
    private var cache = intArrayOf()

    fun integerBreak(n: Int): Int {
        cache = IntArray(n + 1)

        return maxInt(n)
    }

    /**
     dp
     */
    private fun maxInt(n: Int): Int {
        // space can be optimize
        val dp = IntArray(n + 1)
        dp[2] = 1


        for (i in 3..n) {
            val index = (i + 1) / 2
            for (j in 2..index) {
                dp[i] = Math.max(dp[i], Math.max(dp[i - j], i - j) * Math.max(dp[j], j))
            }
        }

        return dp[n]
    }

    // recursion + cache
    private fun maxInt1(n: Int): Int {
        if (n == 2) return 1
        if (n == 3) return 2
        if (n == 4) return 4

        if (cache[n] != 0) {
            return cache[n]
        }

        var res = 0
        val index = (n + 1) / 2

        for (i in 2..index) {
            res = Math.max(res, Math.max(maxInt1(n - i), n - i) * Math.max(maxInt1(i), i))
        }

        cache[n] = res

        return res
    }
}

fun main() {
    val res = Med_343_IntegerBreak.integerBreak(8)
    asserts(res, 18)
}
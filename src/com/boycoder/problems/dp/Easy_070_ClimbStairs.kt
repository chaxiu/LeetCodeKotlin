package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc:
 */
object Easy_070_ClimbStairs {
    private val cache = hashMapOf<Int, Int>()
    fun climbStairs(n: Int): Int {
        return climb2(n)
    }

    // dp
    private fun climb(n: Int): Int {
        if (n == 1) return 1
        if (n == 2) return 2

        val dp = IntArray(n + 1)

        dp[1] = 1
        dp[2] = 2

        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n]
    }

    /**
     * This problems is similar to [com.boycoder.problems.dp.knapsack.Med_377_CombinationSumIV.count3]
     * We got array:[1,2], and target n
     * Get all the permutations count.
     * So it become a knapsack problem.
     */
    private fun climb2(n: Int): Int {
        val dp = IntArray(n + 1)

        dp[0] = 1

        for (i in 1..n) {
            for (j in 1..2)
                if (i >= j) {
                    dp[i] = dp[i] + dp[i - j]
                }
        }

        return dp[n]
    }

    // recursion + cache
    private fun climb1(n: Int): Int {
        if (n == 1) return 1
        if (n == 2) return 2

        if (cache.getOrDefault(n, 0) != 0) {
            return cache.getOrDefault(n, 0)
        }

        val v = climb(n - 1) + climb(n - 2)
        cache.put(n, v)

        return v
    }
}

fun main() {
    val res = Easy_070_ClimbStairs.climbStairs(3)
    asserts(res, 3)
    println(res)
}
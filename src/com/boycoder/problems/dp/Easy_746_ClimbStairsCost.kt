package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc:
 */
object Easy_746_ClimbStairsCost {
    private var cost: IntArray = intArrayOf()
    private var cache = intArrayOf()

    fun minCostClimbingStairs(cost: IntArray): Int {
        this.cost = cost
        cache = IntArray(cost.size + 1)
        return min(cost.size)
    }

    // dp
    private fun min(n: Int): Int {
        val dp = IntArray(n + 1)
        dp[0] = cost[0]
        dp[1] = cost[1]

        for (i in 2..n) {
            val cost = cost.getOrNull(i)?:0
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost
        }

        return dp[n]
    }

    /**
     * To get to n, we need to pay: Math.min(dfs(n - 1) + cost[n], dfs(n - 2) + cost[n])
     */
    private fun min1(n: Int): Int {
        if (n == 0) return cost[0]
        if (n == 1) return cost[1]

        if (cache[n] != 0) {
            return cache[n]
        }

        val v = cost.getOrNull(n)?:0
        val res = Math.min(min1(n - 1) + v, min1(n - 2) + v)
        cache[n] = res
        return res
    }
}

fun main() {
    val res = Easy_746_ClimbStairsCost.minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1))
    asserts(res, 6)
}
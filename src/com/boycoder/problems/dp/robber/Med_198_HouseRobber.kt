package com.boycoder.problems.dp.robber

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc:
 */
object Med_198_HouseRobber {
    private var array = intArrayOf()
    private var memo = intArrayOf()

    fun rob(nums: IntArray): Int {
        array = nums
        memo = IntArray(nums.size){ -1 }
        return dp()
    }

    /**
     * dp
     *
     * bottom to top
     *
     * dp[i] means, for [0, 1] array, we can the max value
     *
     * dp[i] = Math.max(dp[i - 2] + array[i], dp[i - 1])
     */
    private fun dp(): Int {
        val size = array.size
        // corner case
        if (size == 1) return array[0]
        if (size == 2) return Math.max(array[1], array[0])

        val dp = IntArray(array.size) { -1 }
        dp[0] = array[0]
        dp[1] = Math.max(array[1], array[0])

        for (i in 2 until size) {
            dp[i] = Math.max(dp[i - 2] + array[i], dp[i - 1])
        }

        return dp[size - 1]
    }

    /**
     * dfs + cache
     */
    private fun dfs1(end: Int): Int {
        if (end == 0) return array[end]
        if (end == 1) return Math.max(array[0], array[1])

        if (memo[end] != -1) {
            return memo[end]
        }

        val res = Math.max(dfs(end - 2) + array[end], dfs(end - 1))

        memo[end] = res
        return res
    }

    /**
     * dfs
     *
     * Break the array into smaller array
     */
    private fun dfs(end: Int): Int {
        if (end == 0) return array[end]
        if (end == 1) return Math.max(array[0], array[1])

        /**
         * If we take the end, then we can take (end - 1), we can just take (end - 2)
         */
        val res = Math.max(dfs(end - 2) + array[end], dfs(end - 1))

        return res
    }
}

fun main() {
    val res = Med_198_HouseRobber.rob(intArrayOf(1,2,3,1))
    asserts(res, 4)
    val res1 = Med_198_HouseRobber.rob(intArrayOf(2,7,9,3,1))
    asserts(res1, 12)
}
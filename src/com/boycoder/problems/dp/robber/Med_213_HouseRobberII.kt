package com.boycoder.problems.dp.robber

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/house-robber-ii/
 */
object Med_213_HouseRobberII {

    private var array = intArrayOf()
    private var memo = intArrayOf()

    /**
     * In this case, because the head and tail of array is connected.
     * So, the max value is max(dfs(0, end - 1), dfs(1, end)), and end = size - 1
     */
    fun rob(nums: IntArray): Int {
        // Be careful here, ArrayIndexOutOfBoundsException
        if (nums.size ==  1) return nums[0]

        this.array = nums
        this.memo = IntArray(array.size){ -1 }
        val end = nums.size - 1
        val memo1 = IntArray(array.size){ -1 }
        val memo2 = IntArray(array.size){ -1 }
//        return Math.max(dfs1(0, end - 1, memo1), dfs1(1, end, memo2))
        return Math.max(dp(0, end - 1), dp(1, end))
    }

    private fun dp(start: Int, end: Int): Int {
        val dp = IntArray(end + 1) { -1 }

        if (end == start) return array[start]
        if (end - start == 1) return Math.max(array[start], array[end])

        dp[start] = array[start]
        dp[start + 1] = Math.max(array[start], array[start + 1])

        // calculate dp[start + 1] to dp[end]
        for (i in (start + 2)..end) {
            dp[i] = Math.max(dp[i - 2] + array[i], dp[i - 1])
        }

        return dp[end]
    }

    /**
     * Because the dfs1 will be called two times from outside.
     * So, we can not just use one memo array to cache.
     * To solve this problem, we can pass two memo array.
     */
    private fun dfs1(start: Int, end: Int, memo: IntArray): Int {
        if (end == start) return array[end]
        if (end == start + 1) return Math.max(array[start], array[start + 1])

        if (memo[end] != -1) {
            return memo[end]
        }
        /**
         * If we take the end, then we can take (end - 1), we can just take (end - 2)
         */
        val res = Math.max(dfs1(start, end - 2, memo) + array[end], dfs1(start, end - 1, memo))

        memo[end] = res
        return res
    }

    /**
     * dfs
     *
     * Break the array into smaller array
     * Time out
     */
    private fun dfs(start: Int, end: Int): Int {
        if (end == start) return array[end]
        if (end == start + 1) return Math.max(array[start], array[start + 1])

        /**
         * If we take the end, then we can take (end - 1), we can just take (end - 2)
         */
        val res = Math.max(dfs(start, end - 2) + array[end], dfs(start, end - 1))

        return res
    }
}

fun main() {
    val res = Med_213_HouseRobberII.rob(intArrayOf(2,3,2))
    asserts(res, 3)
    val res1 = Med_213_HouseRobberII.rob(intArrayOf(1,2,3,1))
    asserts(res1, 4)
}
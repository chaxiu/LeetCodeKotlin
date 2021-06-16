package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
object Med_300_LongestIncreasingSubsequence {
    /**
     * dp
     *
     * dp[i] means [0, i] array LIS size
     * Try to break this problem into smaller problems
     */
    fun find(array: IntArray): Int {
        // init default value as 1
        val dp = IntArray(array.size) { 1 }
        for (i in 1 until array.size) {
            for (j in 0 until i) {
                if (array[i] > array[j]) {
                    // dp[i] default value is 1
                    dp[i] = Math.max(dp[i], dp[j] + 1)
                }
            }
        }

        return dp[array.size - 1]
    }
}

fun main() {
    val res = Med_300_LongestIncreasingSubsequence.find(intArrayOf(10,9,2,5,3,7,101,18))
    asserts(res, 4)
    val res1 = Med_300_LongestIncreasingSubsequence.find(intArrayOf(0,1,0,3,2,3))
    asserts(res1, 4)
    val res2 = Med_300_LongestIncreasingSubsequence.find(intArrayOf(7,7,7,7,7,7,7))
    asserts(res2, 1)
}

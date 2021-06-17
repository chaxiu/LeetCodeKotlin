package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/17
 * @desc: https://leetcode-cn.com/problems/uncrossed-lines/
 * This is similar to [Med_1143_LongestCommonSubsequence]
 * DP + DFS
 */
object Med_1035_UncrossedLines {
    fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        return dp(nums1, nums2)
    }

    /**
     * dp
     *
     * dp[i][j] means nums1[0, i -1], nums2[0, j - 1] LCS size
     */
    private fun dp(nums1: IntArray, nums2: IntArray): Int {
        val size1 = nums1.size
        val size2 = nums2.size

        // dp array + init
        val dp = Array(size1 + 1){IntArray(size2 + 1) { 0 } }

        for (i in 1..size1) {
            for (j in 1..size2) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])
                }
            }
        }

        return dp[size1][size2]
    }
}

fun main() {
    val res = Med_1035_UncrossedLines.maxUncrossedLines(intArrayOf(1, 4, 2), intArrayOf(1, 2, 4))
    asserts(res, 2)
    val res1 = Med_1035_UncrossedLines.maxUncrossedLines(intArrayOf(2,5,1,2,5), intArrayOf(10,5,2,1,5,2))
    asserts(res1, 3)
}